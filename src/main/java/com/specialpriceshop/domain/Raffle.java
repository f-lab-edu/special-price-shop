package com.specialpriceshop.domain;

import com.specialpriceshop.common.entity.BaseTimeEntity;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Raffle extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double rafflePrice;

    private LocalDateTime raffleStartDate;

    private LocalDateTime raffleEndDate;

    private LocalDateTime drawDate;

    private LocalDateTime paymentDueDate;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public Raffle(
        final double rafflePrice,
        final LocalDateTime raffleStartDate,
        final LocalDateTime raffleEndDate,
        final LocalDateTime drawDate,
        final LocalDateTime paymentDueDate
    ) {
        this.rafflePrice = rafflePrice;
        this.raffleStartDate = raffleStartDate;
        this.raffleEndDate = raffleEndDate;
        this.drawDate = drawDate;
        this.paymentDueDate = paymentDueDate;
    }
}
