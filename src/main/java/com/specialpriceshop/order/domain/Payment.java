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

    public void pay(final BigDecimal requestAmount) {
        paymentAfterCheck();
        if (requestAmount.compareTo(this.amount) != 0) {
            throw new RuntimeException("잘못된 금액");
        }
        paymentStatus = PaymentStatus.PAYMENT;
    }

    public void paymentAfterCheck() {
        if (!paymentStatus.equals(PaymentStatus.NONE)) {
            throw new RuntimeException("이미 결제 되었거나 취소된 결제건");
        }
    }
}
