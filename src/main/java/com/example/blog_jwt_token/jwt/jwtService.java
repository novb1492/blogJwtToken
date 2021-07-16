package com.example.blog_jwt_token.jwt;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.blog_jwt_token.config.principaldetail;
import com.example.blog_jwt_token.model.jwt.jwtDao;
import com.example.blog_jwt_token.model.jwt.jwtDto;
import com.example.blog_jwt_token.model.user.userDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class jwtService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private jwtDao jwtDao;

    public String getJwtToken(int id) {
        System.out.println("토큰 제작시작");
        return JWT.create().withSubject("jwtToken").withExpiresAt(new Date(System.currentTimeMillis()+1000)).withClaim("id",id).sign(Algorithm.HMAC512("kim"));
    }
    public Authentication getAuthentication(userDto dto) {
        principaldetail principaldetail=new principaldetail(dto);
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPwd(),principaldetail.getAuthorities()));
    }
    public void setSecuritySession(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    public String getJwtToken() {
        System.out.println("리프레시 토큰 제작시작");
        return JWT.create().withSubject("jwtToken").withExpiresAt(new Date(System.currentTimeMillis()+(60000*10))).sign(Algorithm.HMAC512("kim"));
    }
    public void insertRefreshToken(String refreshToken,int userid) {
        try {
            jwtDto jwtDto=new jwtDto(0, refreshToken, userid, null);
            jwtDao.save(jwtDto);
        } catch (Exception e) {
            e.printStackTrace();
            
        } 
    }
    public String getNewJwtToken(jwtDto jwtDto) {
        System.out.println(jwtDto.getUserid());
        if(jwtDto.getTokenName()!=null){
            return getJwtToken(jwtDto.getUserid());
        }else{
            System.out.println("존재하지 않는 토큰");
        }
        return null;
    }
    public jwtDto getRefreshToken(String refreshToken) {
        System.out.println(refreshToken+" 찾기");
        return jwtDao.findByTokenName(refreshToken);
    }
    public String replaceBearer(String token) {
        return  token.replace("Bearer ", "");
    }

   
}
