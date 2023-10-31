package com.specialpriceshop.account.domain;

import com.fasterxml.uuid.Generators;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class AccountId implements Serializable {

    @Serial
    private static final long serialVersionUID = 8170920426703866878L;

    @Column(name = "account_id")
    private String value;
    private AccountId(String uuid) {
        this.value = uuid;
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountId accountId = (AccountId) o;
        return Objects.equals(value, accountId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
