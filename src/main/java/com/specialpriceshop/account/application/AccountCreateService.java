package com.specialpriceshop.account.application;

import com.specialpriceshop.account.domain.Account;
import com.specialpriceshop.account.dto.AccountCreateRequest;
import com.specialpriceshop.account.exception.EmailDuplicatedException;
import com.specialpriceshop.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountCreateService {

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String createAccount(final AccountCreateRequest request) {
        final Account create = accountMapper.mapToAccountEntity(request, passwordEncoder);

        validate(create.getEmail());

        return accountRepository
            .save(create)
            .getAccountId()
            .getId();
    }


    private void validate(final String email) {
        if (accountRepository.existsByEmail(email)) {
            throw new EmailDuplicatedException();
        }
    }
}
