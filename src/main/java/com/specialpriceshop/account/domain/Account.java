package com.specialpriceshop.account.domain;

import com.specialpriceshop.common.entity.BaseTimeEntity;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseTimeEntity {

    @EmbeddedId
    @AttributeOverride(name = "value",
        column = @Column(name = "id"))
    private AccountId id;

    private String email;

    private String password;

    private String nickname;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean remove;

    @Builder
    public Account(
        final String email,
        final String password,
        final String nickname,
        final String phone,
        final Role role,
        final boolean remove
    ) {
        this.phone = phone;
        this.remove = remove;
        this.id = AccountId.create();
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }
}
