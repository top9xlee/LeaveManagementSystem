package com.example.leavemanagementsystem.config;

import com.example.leavemanagementsystem.service.userService.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.*;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/login", "/doLogin", "/forget-password", "/doForget", "/verify/**", "/api/**",
                        "/changePassword")
                .permitAll()
                .anyRequest().authenticated();
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        http.formLogin()
                .loginPage("/login")//
                .loginProcessingUrl("/doLogin")
                .defaultSuccessUrl("/user", true)//
                .failureUrl("/login?error=true")//
                .passwordParameter("password")//
                .usernameParameter("email")//
                .and().logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login");

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("word/**", "/app.js/**", "/app/**", "/demo/**", "/image/**", "/snippets/**", "/vendors/**",
                        "/style.css"); // #3
    }
}
