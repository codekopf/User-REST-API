package com.codekopf.api.security;

import com.codekopf.api.application.users.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String REALM="USER_API_REALM";

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {

        /* Hashing password */
//        BCryptPasswordEncoder encoder = passwordEncoder();
//        auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("test")).roles("ADMIN");

        /* Password send as plaintext */
        auth.inMemoryAuthentication().withUser("John").password("{noop}test").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("Oliver").password("{noop}test").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,UserController.USER_API_PATH + "/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, UserController.USER_API_PATH + "/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, UserController.USER_API_PATH + "/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, UserController.USER_API_PATH + "/**").hasRole("ADMIN")
                .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.
    }

    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
        return new CustomBasicAuthenticationEntryPoint();
    }
}