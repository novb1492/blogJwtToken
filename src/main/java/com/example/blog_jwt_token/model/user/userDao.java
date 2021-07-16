package com.example.blog_jwt_token.model.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface userDao  extends JpaRepository<userDto,Integer>{
    userDto findByEmail(String email);
}
