package com.demo.security.configuration;


import com.demo.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder encodePassword(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf().disable()
                .cors().disable()
                .logout((logout) -> logout
                        .logoutUrl("/logout").permitAll()
//                        .logoutSuccessUrl("/login")
                )
                .logout(withDefaults())
                .formLogin((login) ->login
//                        .loginPage("/login").permitAll()
//                        .loginProcessingUrl("/login").permitAll()
                        .defaultSuccessUrl("/").permitAll()
                )
//                .formLogin().disable()
                .httpBasic().disable()
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers("/session").permitAll()
                        .requestMatchers("/join").permitAll()
                        .requestMatchers("/amdin").hasRole("ADMIN")

//                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling((exception) -> exception
                        .accessDeniedPage("/accessDeniedPage")
                )
                .build();
    }


}
