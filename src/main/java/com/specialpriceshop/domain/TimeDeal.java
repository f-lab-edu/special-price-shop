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
public class TimeDeal extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double timeDealPrice;

    private LocalDateTime dealStartDate;

    private LocalDateTime dealEndDate;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public TimeDeal(
        final double timeDealPrice,
        final LocalDateTime dealStartDate,
        final LocalDateTime dealEndDate
    ) {
        this.timeDealPrice = timeDealPrice;
        this.dealStartDate = dealStartDate;
        this.dealEndDate = dealEndDate;
    }
}
