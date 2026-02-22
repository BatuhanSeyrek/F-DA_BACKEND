package com.fida.fida_backend.controller.user;

import com.fida.fida_backend.dto.AuthRequest;
import com.fida.fida_backend.dto.DtoUser;
import com.fida.fida_backend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    public UserService userService;
    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody AuthRequest request){
        return ResponseEntity.ok(userService.login(request));
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody DtoUser request){
        return userService.register(request);
    }
}
