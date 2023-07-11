package com.specialpriceshop.account.repository;

import com.specialpriceshop.account.domain.Account;
import com.specialpriceshop.account.domain.AccountId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, AccountId> {

    boolean existsByEmail(final String email);

    Optional<Account> findByEmail(final String email);

}
