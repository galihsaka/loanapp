package com.enigma.controller;

import com.enigma.dto.request.AuthRequest;
import com.enigma.dto.request.CustomerRequest;
import com.enigma.dto.request.GenericAuthRequest;
import com.enigma.dto.request.RegisterRequest;
import com.enigma.dto.response.CommonResponse;
import com.enigma.dto.response.LoginResponse;
import com.enigma.dto.response.RegisterResponse;
import com.enigma.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authenticationService;

    @Autowired
    public AuthController(AuthService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<CommonResponse<RegisterResponse>> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = authenticationService.register(request);
        CommonResponse<RegisterResponse> registerResponseCommonResponse=new CommonResponse<>();
        registerResponseCommonResponse.setStatusCode(HttpStatus.CREATED.value());
        registerResponseCommonResponse.setMessage("Account Registered Succesfully");
        registerResponseCommonResponse.setData(Optional.of(response));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerResponseCommonResponse);
    }

    @PostMapping("/register/customer")
    public ResponseEntity<CommonResponse<RegisterResponse>> registerCustomer(@RequestBody GenericAuthRequest<CustomerRequest> request) {
        RegisterResponse response=authenticationService.registerCustomer(request);
        CommonResponse<RegisterResponse> registerResponseCommonResponse=new CommonResponse<>();
        registerResponseCommonResponse.setStatusCode(HttpStatus.CREATED.value());
        registerResponseCommonResponse.setMessage("Account Registered Succesfully");
        registerResponseCommonResponse.setData(Optional.of(response));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerResponseCommonResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse<LoginResponse>> login(@RequestBody AuthRequest authRequest){
        LoginResponse loginResponse=authenticationService.login(authRequest);
        CommonResponse<LoginResponse> loginResponseCommonResponse=new CommonResponse<>();
        loginResponseCommonResponse.setStatusCode(HttpStatus.OK.value());
        loginResponseCommonResponse.setMessage("Login Success");
        loginResponseCommonResponse.setData(Optional.of(loginResponse));
        return ResponseEntity.ok(loginResponseCommonResponse);
    }
}
