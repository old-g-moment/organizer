package com.example.authservice.JwtAuth;

import com.example.authservice.Interfaces.JwtValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class JwtValidatorImpl implements JwtValidator {
    private static JwtValidatorImpl instance;

    public static JwtValidator getInstance() {
        if (instance == null) {
            instance = new JwtValidatorImpl();
            return instance;
        }
        return instance;
    }

    @Override
    public boolean checkJwtToken(@NonNull String token) {
        return true;
    }
}
