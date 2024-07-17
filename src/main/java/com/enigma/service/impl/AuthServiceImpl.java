package com.enigma.service.impl;

import com.enigma.dto.request.AuthRequest;
import com.enigma.dto.request.CustomerRequest;
import com.enigma.dto.request.RegisterRequest;
import com.enigma.dto.response.LoginResponse;
import com.enigma.dto.response.RegisterResponse;
import com.enigma.entity.AppUser;
import com.enigma.entity.Customer;
import com.enigma.entity.Role;
import com.enigma.repository.CustomerRepository;
import com.enigma.repository.ProfilePictureRepository;
import com.enigma.repository.UserRepository;
import com.enigma.security.JwtUtil;
import com.enigma.service.AuthService;
import com.enigma.service.RoleService;
import com.enigma.service.UserService;
import com.enigma.utils.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private RoleService roleService;
    private CustomerRepository customerService;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    private UserService userService;
    private ProfilePictureRepository profilePictureRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, UserService userService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, CustomerRepository customerService, RoleService roleService,
                           ProfilePictureRepository profilePictureRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.customerService = customerService;
        this.roleService = roleService;
        this.profilePictureRepository=profilePictureRepository;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        List<Role.ERole> storeRole=request.getRoles();
        for(int i=0;i< storeRole.size();i++){
            if(storeRole.get(i).name().equals("ROLE_CUSTOMER")){
                throw new CommonException("Wrong Endpoint");
            }
        }
        List<Role> roles=roleService.getOrSave(request.getRoles());
        AppUser user=new AppUser();
        user.setEmail(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(roles);
        AppUser saveUser=userRepository.save(user);
        RegisterResponse registerResponse=new RegisterResponse();
        registerResponse.setEmail(saveUser.getUsername());
        registerResponse.setRoles(saveUser.getRoles());
        return registerResponse;
    }

    @Override
    public RegisterResponse registerCustomer(CustomerRequest request, MultipartFile file) {
        List<Role.ERole> storeRole=new ArrayList<>();
        storeRole.add(Role.ERole.valueOf("ROLE_CUSTOMER"));
        List<Role> roles=roleService.getOrSave(storeRole);
        String oriFileName= file.getOriginalFilename();
        return null;
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        request.getPassword(),
                        userDetails.getAuthorities()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AppUser appUser = (AppUser) authentication.getPrincipal();
        String token = jwtUtil.createToken(appUser);
        LoginResponse loginResponse=new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setRoles(appUser.getRoles());
        loginResponse.setEmail(appUser.getUsername());
        return loginResponse;
    }
}
