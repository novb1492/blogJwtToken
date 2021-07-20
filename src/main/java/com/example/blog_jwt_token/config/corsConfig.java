package com.example.blog_jwt_token.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class corsConfig {

   @Bean
   public CorsFilter corsFilter() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowCredentials(true);
      config.addAllowedOrigin("http://localhost:9090"); // e.g. http://domain1.com
      config.addAllowedHeader("*");
      config.addAllowedMethod("*");
      config.addExposedHeader("Authorization");
      config.addExposedHeader("refreshToken");
      source.registerCorsConfiguration("/**", config);
      return new CorsFilter(source);
   }

}
