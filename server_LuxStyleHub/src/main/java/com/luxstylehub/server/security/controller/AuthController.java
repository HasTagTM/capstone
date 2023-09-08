package com.luxstylehub.server.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luxstylehub.server.security.payload.JWTAuthResponse;
import com.luxstylehub.server.security.payload.LoginDto;
import com.luxstylehub.server.security.payload.RegisterDto;
import com.luxstylehub.server.security.payload.RegisterResponse;
import com.luxstylehub.server.security.service.AuthService;



@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Build Login REST API
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
           	
    	String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setUsername(loginDto.getUsername());
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    // Build Register REST API
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto){
    	RegisterResponse response = authService.register(registerDto);
        return new ResponseEntity<RegisterResponse>(response, HttpStatus.CREATED);
    }
    
    // JSON inviato dal Client
    /*{
        "name": "Giuseppe",
        "lastname": "Verdi",
        "username": "giuseppevardi",
        "email": "g.verdi@example.com",
        "password": "qwerty",
        "roles": ["MODERATOR", "ADMIN"]
    }*/
    
//    "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJoLnNhaHJhb3VpQGV4YW1wbGUuY29tIiwiaWF0IjoxNjk0M
//    TEzOTIzLCJleHAiOjE2OTQ5Nzc5MjN9.S5dYLkjdfYVxsbTkv79LIieu7
//    WRlx6mHgTbdm1-0UCrEkGa68FL24YP2RMT3lxdR",
}
