package com.studentresults.service;

import com.studentresults.dto.AuthResponse;
import com.studentresults.dto.LoginRequest;
import com.studentresults.dto.RegisterRequest;
import com.studentresults.dto.ServiceResult;

public interface AuthService {

    ServiceResult<AuthResponse> login(LoginRequest request);

    ServiceResult<AuthResponse> register(RegisterRequest request);

    ServiceResult<AuthResponse> refreshToken(String refreshToken);
}
