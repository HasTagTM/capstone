package com.luxstylehub.server.security.payload;

import java.util.Set;

import com.luxstylehub.server.security.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthResponse {
	private String username;
    private String accessToken;
    private String tokenType = "Bearer";
	public void setRoles(Set<Role> roles) {
		// TODO Auto-generated method stub
		
	}
}
