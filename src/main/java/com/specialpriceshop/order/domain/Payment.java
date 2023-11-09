package com.specialpriceshop.order.domain;

import com.specialpriceshop.common.entity.BaseTimeEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @LastModifiedDate
    private LocalDateTime actionAt;

    @Builder
    public Payment(
        final Long id,
        final BigDecimal amount,
        final PaymentStatus paymentStatus,
        final LocalDateTime actionAt
    ) {
        this.id = id;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.actionAt = actionAt;
    }

    public void pay() {
        paymentStatus = PaymentStatus.PAYMENT;
    }

    public boolean isAvailable() {
        return paymentStatus.equals(PaymentStatus.NONE);
    }

    public int amountCheck(BigDecimal requestAmount) {
        return amount.compareTo(requestAmount);
    }
}
