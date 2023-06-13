package com.specialpriceshop.account.domain;

import com.specialpriceshop.common.entity.BaseTimeEntity;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Account extends BaseTimeEntity {

    @EmbeddedId
    private AccountId accountId;

    private String email;

    private String password;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Account(
        final String email,
        final String password,
        final String nickname,
        final Role role
    ) {
        this.accountId = AccountId.create();
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }
}
