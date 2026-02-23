package com.fida.fida_backend.service.user;

import com.fida.fida_backend.entity.User;
import com.fida.fida_backend.repository.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service // Bu anatasyon Spring'in bu sınıfı bulup JwtFilter'a enjekte etmesini sağlar
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Veritabanında findByUserName ile kullanıcıyı arıyoruz
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + username));

        // Spring'in anlayacağı UserDetails formatına dönüştürüyoruz
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                new ArrayList<>() // Şimdilik yetki/rol listesi boş
        );
    }
}