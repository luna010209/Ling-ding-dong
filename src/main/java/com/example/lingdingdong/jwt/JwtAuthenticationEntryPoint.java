package com.example.lingdingdong.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
   
   private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
   private final ObjectMapper objectMapper = new ObjectMapper();
   
   @Override
   public void commence(HttpServletRequest request,
                        HttpServletResponse response,
                        AuthenticationException authException) throws IOException {
      
      logger.warn("인증되지 않은 요청: {} {}", request.getMethod(), request.getRequestURI());
      
      // 토큰 만료 헤더가 있는 경우 토큰 만료 응답
      String tokenExpiredHeader = request.getHeader(JwtFilter.TOKEN_EXPIRED_HEADER);
      if ("true".equals(tokenExpiredHeader)) {
         response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
         response.setContentType(MediaType.APPLICATION_JSON_VALUE);
         response.setCharacterEncoding("UTF-8");
         
         Map<String, Object> errorResponse = new HashMap<>();
         errorResponse.put("error", "TOKEN_EXPIRED");
         errorResponse.put("message", "JWT 토큰이 만료되었습니다. 새로운 토큰을 발급받아주세요.");
         errorResponse.put("status", HttpServletResponse.SC_UNAUTHORIZED);
         
         response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
         return;
      }
      
      // 일반적인 인증 실패 응답
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.setCharacterEncoding("UTF-8");
      
      Map<String, Object> errorResponse = new HashMap<>();
      errorResponse.put("error", "UNAUTHORIZED");
      errorResponse.put("message", "유효한 인증 정보가 필요합니다.");
      errorResponse.put("status", HttpServletResponse.SC_UNAUTHORIZED);
      
      response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
   }
}