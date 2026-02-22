package com.fida.fida_backend.service.user;

import com.fida.fida_backend.dto.AuthRequest;
import com.fida.fida_backend.dto.DtoUser;
import com.fida.fida_backend.entity.user.User;
import com.fida.fida_backend.repository.user.UserRepository;
import com.fida.fida_backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    public JwtUtil jwtUtil;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    private AuthenticationManager authManager;
    public Map<String,Object> login(AuthRequest request){
        try{
            authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        }
        catch (Exception e){
            Map<String,Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.UNAUTHORIZED.value());
            errorResponse.put("error","Hatalı giriş");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse).getBody();
        }
        Optional<User> user = userRepository.findByUserName(request.getUsername());
        String token = jwtUtil.generateToken(request.getUsername());
        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("token",token);
        responseMap.put("userName",request.getUsername());
        responseMap.put("id",user.get().getId());
        return responseMap;

    }
    public ResponseEntity<String> register(DtoUser request){
        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body("Bu kullanıcı maili ile kayıt daha önceden yapılmış");
        }
        User newUser = new User();
        newUser.setUserName(request.getUserName());
        newUser.setPassword(passwordEncoder.encode( request.getPassword()));
        newUser.setEmail(request.getEmail());
        newUser.setPhoneNumber(request.getPhoneNumber());
        return ResponseEntity.ok().body(String.valueOf(userRepository.save(newUser))) ;
    }
}
