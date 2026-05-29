package com.studentresults.service.impl;

import com.studentresults.dto.AuthResponse;
import com.studentresults.dto.LoginRequest;
import com.studentresults.dto.RegisterRequest;
import com.studentresults.dto.ServiceResult;
import com.studentresults.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    // TODO: inject UserRepository, RoleRepository, JwtService,
    //        PasswordEncoder, AuthenticationManager

    @Override
    public ServiceResult<AuthResponse> login(LoginRequest request) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public ServiceResult<AuthResponse> register(RegisterRequest request) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public ServiceResult<AuthResponse> refreshToken(String refreshToken) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
