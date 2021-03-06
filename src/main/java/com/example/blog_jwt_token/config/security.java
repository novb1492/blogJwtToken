package com.example.blog_jwt_token.config;


import com.example.blog_jwt_token.jwt.jwtAuthorizationFilter;
import com.example.blog_jwt_token.jwt.jwtLoginFilter;
import com.example.blog_jwt_token.jwt.jwtService;
import com.example.blog_jwt_token.model.user.userDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import lombok.RequiredArgsConstructor;

@Configuration//빈등록: 스프링 컨테이너에서 객체에서 관리
@EnableWebSecurity/////필터를 추가해준다
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class security extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private userDao dao;
    @Autowired
    private jwtService jwtService;
    @Autowired
    private corsConfig corsConfig;
 
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    @Bean
    public BCryptPasswordEncoder pwdEncoder() {
       return  new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .addFilter(corsConfig.corsFilter())
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .formLogin().disable()
        .httpBasic().disable()
        .addFilter(new jwtAuthorizationFilter(authenticationManager(),dao,jwtService))
        .addFilter(new jwtLoginFilter(jwtService))
        .authorizeRequests()
        .antMatchers("/","/auth/**","/login")////이 링크들은
        .permitAll()///허용한다
        .antMatchers("/api/v1/user/**")
        .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGE') or hasRole('ROLE_ADMIN')")
        .antMatchers("/api/v1/manage/**")
        .access("hasRole('ROLE_MANAGE') or hasRole('ROLE_ADMIN')")
        .antMatchers("/api/v1/admin/**")
        .access("hasRole('ROLE_ADMIN')")
        .anyRequest()///그외 다른 요청운
        .authenticated();//인증이있어야한다(로그인) 

    }
}
