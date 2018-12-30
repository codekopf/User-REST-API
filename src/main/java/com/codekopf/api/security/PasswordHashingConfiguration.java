package com.codekopf.api.security;

import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordHashingConfiguration {

    /* Bean Configuration for simple password hashing */
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    /* Currently used no PasswordEncoder for password hashing */
//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }
}
