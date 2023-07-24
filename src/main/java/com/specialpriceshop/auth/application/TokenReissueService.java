package com.specialpriceshop.auth.application;

import com.specialpriceshop.account.domain.Account;
import com.specialpriceshop.account.domain.AccountId;
import com.specialpriceshop.account.exception.AccountNotFoundException;
import com.specialpriceshop.account.repository.AccountRepository;
import com.specialpriceshop.auth.domain.RefreshToken;
import com.specialpriceshop.auth.domain.RefreshTokenRepository;
import com.specialpriceshop.auth.domain.RefreshTokenValidator;
import com.specialpriceshop.auth.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenReissueService {

    private final RefreshTokenValidator refreshTokenValidator;
    private final TokenCreateService tokenCreateService;
    private final AccountRepository accountRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public LoginResponse reIssue(
        final String refreshToken
    ) {

        final RefreshToken getToken = getRefreshToken(refreshToken);
        final AccountId accountId = AccountId.of(getToken.getUserId());

        validRefreshToken(accountId, refreshToken);

        final Account account = accountRepository.findById(accountId)
            .orElseThrow(AccountNotFoundException::new);

        final UserDetails principal = User.builder()
            .username(account.getId().getValue())
            .password("N/A")
            .authorities(account.getRole().name())
            .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(
            principal, null, principal.getAuthorities());

        return new LoginResponse(tokenCreateService.createAccessToken(authentication));
    }

    private RefreshToken getRefreshToken(final String refreshToken) {
        return refreshTokenRepository.findByValue(refreshToken)
            .orElseThrow(() -> new BadCredentialsException("리프레쉬 토큰이 유효하지 않습니다."));
    }

    private void validRefreshToken(final AccountId accountId, final String refreshToken) {
        if (!refreshTokenValidator.validate(accountId.getValue(), refreshToken)) {
            throw new BadCredentialsException("리프레쉬 토큰이 유효하지 않습니다.");
        }
    }
}
