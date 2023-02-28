package com.demo.security.security;

import com.demo.security.entity.User;
import com.demo.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PricipalDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("PricipalDetailsService: " + username);
        try {
            User user = userRepository.findByEmail(username);
            if (user == null) {
                throw new InternalAuthenticationServiceException("UserDetailService returnde null");
            }
            return new PrincipalDetails(user);

        } catch (InternalAuthenticationServiceException ex) {
            throw ex;
        } catch(Exception e){
            log.error(e.getMessage());
        }
        return null;
    }
}
