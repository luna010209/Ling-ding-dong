package com.example.lingdingdong.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
   
   private static final Logger logger = LoggerFactory.getLogger(JwtAccessDeniedHandler.class);
   private final ObjectMapper objectMapper = new ObjectMapper();
   
   @Override
   public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
      logger.warn("접근 권한이 없는 요청: {} {} - {}", request.getMethod(), request.getRequestURI(), accessDeniedException.getMessage());
      
      // 403 Forbidden 응답
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.setCharacterEncoding("UTF-8");
      
      Map<String, Object> errorResponse = new HashMap<>();
      errorResponse.put("error", "ACCESS_DENIED");
      errorResponse.put("message", "해당 리소스에 접근할 권한이 없습니다.");
      errorResponse.put("status", HttpServletResponse.SC_FORBIDDEN);
      
      response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
   }
}