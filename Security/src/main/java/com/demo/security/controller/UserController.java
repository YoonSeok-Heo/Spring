package com.demo.security.controller;

import com.demo.security.entity.User;
import com.demo.security.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String main(){
        return "main page";
    }

    @PostMapping("/join")
    public String join(@RequestBody User user){

        log.info(user.toString());
        try{
            user.encryptPassword(passwordEncoder);
            userRepository.save(user);
            return "join success";
        } catch (Exception e){
            return "join fail";
        }
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin page";
    }

    @GetMapping("/accessDeniedPage")
    public String accessDeniedPage(){
        return "Access Denied Page";
    }

    @GetMapping("/session")
    public String sessionInfo(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null) {
            return "세션이 없습니다";
        }

        log.info("sessionId = {}", session.getId());
        log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval());
        log.info("creationTime={}", new Date(session.getCreationTime()));
        log.info("lastAccessTime={}", new Date(session.getLastAccessedTime()));

        return "세션출력";

    }
}
