package com.rfid.ternakbromonilan.service;

import com.rfid.ternakbromonilan.dto.UserRegistrationDto;
import com.rfid.ternakbromonilan.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);
    User save(UserRegistrationDto registration);
}
