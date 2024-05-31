package com.cpa.jwt.AuthenticatorService.config;

import java.util.Map;

import com.cpa.jwt.AuthenticatorService.entity.User;

public interface JwtGeneratorInterface {
    Map<String, String> generateToken(User user);

}
