package com.luxstylehub.server.security.service;

import com.luxstylehub.server.security.payload.LoginDto;
import com.luxstylehub.server.security.payload.RegisterDto;
import com.luxstylehub.server.security.payload.RegisterResponse;

public interface AuthService {
    
	String login(LoginDto loginDto);
	RegisterResponse register(RegisterDto registerDto);
    
}
