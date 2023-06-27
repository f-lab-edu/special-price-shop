package com.specialpriceshop.account.domain;

import com.fasterxml.uuid.Generators;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountId implements Serializable {

    private String id;

    private AccountId(String uuid) {
        this.id = uuid;
    }

    public static AccountId create() {
        return new AccountId(
            Generators
                .timeBasedEpochGenerator()
                .generate()
                .toString()
        );
    }

    public static AccountId of(String uuid) {
        return new AccountId(uuid);
    }
}
