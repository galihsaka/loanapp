package com.enigma.service;

import com.enigma.dto.request.AuthRequest;
import com.enigma.dto.request.CustomerRequest;
import com.enigma.dto.request.RegisterRequest;
import com.enigma.dto.response.LoginResponse;
import com.enigma.dto.response.RegisterResponse;
import org.springframework.web.multipart.MultipartFile;

public interface AuthService {
    public RegisterResponse register(RegisterRequest request);
    public RegisterResponse registerCustomer(CustomerRequest request, MultipartFile file);
    public LoginResponse login(AuthRequest request);
}
