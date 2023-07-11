package com.specialpriceshop.account.repository;

import com.specialpriceshop.account.domain.Account;
import com.specialpriceshop.account.domain.AccountId;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, AccountId> {

    boolean existsByEmail(final String email);

    Optional<Account> findByEmail(final String email);

}
