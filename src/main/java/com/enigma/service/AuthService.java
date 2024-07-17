package com.enigma.service;

import com.enigma.dto.request.AuthRequest;
import com.enigma.dto.request.CustomerRequest;
import com.enigma.dto.request.RegisterRequest;
import com.enigma.dto.response.LoginResponse;
import com.enigma.dto.response.RegisterResponse;

public interface AuthService {
    public RegisterResponse register(RegisterRequest request);
    public RegisterResponse registerCustomer(CustomerRequest request);
    public LoginResponse login(AuthRequest request);
}
