package com.specialpriceshop.auth.dto;

import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public Authentication toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
