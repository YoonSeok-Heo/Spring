package com.demo.security.controller;

import com.demo.security.entity.User;
import com.demo.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String main(){
        return "login success";
    }

    @PostMapping("/join")
    public String join(@RequestBody User user){

        log.info(user.toString());
        try{
        user.encryptPassword(passwordEncoder);
        userRepository.save(user);
            return "success";
        } catch (Exception e){
            return "fail";
        }
    }


}
