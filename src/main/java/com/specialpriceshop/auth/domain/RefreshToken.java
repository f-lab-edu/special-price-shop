package com.specialpriceshop.auth.domain;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash(value = "refreshToken")
public class RefreshToken implements Serializable {

    @Id
    private String userId;

    @Indexed
    private String value;

    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    private long timeOut;

    public RefreshToken(
        final String userId,
        final String value,
        final Long timeOut
    ) {
        this.userId = userId;
        this.value = value;
        this.timeOut = timeOut;
    }

    public boolean hasSameValue(final String value) {
        return this.value.equals(value);
    }
}
