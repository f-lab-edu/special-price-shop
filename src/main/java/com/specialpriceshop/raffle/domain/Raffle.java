package com.specialpriceshop.raffle.domain;

import com.specialpriceshop.common.entity.BaseTimeEntity;
import com.specialpriceshop.item.domain.Item;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Embedded
    private RaffleTimeInfo raffleTimeInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public Raffle(
        final double rafflePrice,
        final LocalDateTime raffleStartDate,
        final LocalDateTime raffleEndDate,
        final LocalDateTime drawDate,
        final LocalDateTime paymentDueDate,
        final Item item
    ) {
        this.rafflePrice = rafflePrice;
        this.raffleTimeInfo = new RaffleTimeInfo(raffleStartDate, raffleEndDate, drawDate,
            paymentDueDate);
        this.item = item;
    }
}
