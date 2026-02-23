package com.fida.fida_backend.security;

import com.fida.fida_backend.repository.user.UserRepository;
import com.fida.fida_backend.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private  JwtUtil jwtUtil;
    @Autowired
    private com.fida.fida_backend.repository.user.UserRepository userRepository;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("Authorization header yok veya hatalı");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Yetkisiz erişim - Authorization header eksik veya yanlış");
            return false; // 403 gönder ve işlemi kes
        }

        String token = authHeader.substring(7);
        String userName = jwtUtil.extraUserName(token);
        System.out.println("Token'dan adminName: " + userName);

        if (userName == null) {
            System.out.println("Token geçersiz veya adminName alınamadı");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Yetkisiz erişim - Token geçersiz");
            return false;
        }

        // AdminInterceptor.java içinde
        Long userId = Long.valueOf(userRepository.findByUserName(userName)
                .map(user -> user.getId()) // Eğer Admin bulunur ve getId() metodu Long döndürürse
                .orElse(null)); // Eğer Admin bulunamazsa adminId null olur


// Şimdi adminId'nin null olup olmadığını kontrol edebilirsiniz
        if (userId == null) {
            System.err.println("AdminInterceptor: Token'dan gelen adminName '" + userName + "' ile Admin bulunamadı.");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Yetkisiz erişim - Admin bulunamadı");
            return false;
        }

        request.setAttribute("userId", userId);

        return true;
    }
}
