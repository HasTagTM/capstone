package com.luxstylehub.server.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luxstylehub.server.security.entity.ERole;
import com.luxstylehub.server.security.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
	Optional<Role> findByRoleName(ERole roleName);

}
