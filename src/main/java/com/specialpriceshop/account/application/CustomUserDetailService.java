package com.specialpriceshop.account.application;

import static org.springframework.security.core.userdetails.User.builder;

import com.specialpriceshop.account.domain.Account;
import com.specialpriceshop.account.exception.AccountNotFoundException;
import com.specialpriceshop.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return accountRepository.findByEmail(username)
            .map(this::toUserDetails)
            .orElseThrow(AccountNotFoundException::new);
    }

    public UserDetails toUserDetails(
        final Account account) {
        return builder()
            .username(account.getId().getValue())
            .password(account.getPassword())
            .authorities(new SimpleGrantedAuthority(account.getRole().name()))
            .disabled(account.isRemove())
            .build();
    }

}
