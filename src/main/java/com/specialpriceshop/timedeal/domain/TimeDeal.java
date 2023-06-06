package com.specialpriceshop.timedeal.domain;

import com.specialpriceshop.common.entity.BaseTimeEntity;
import com.specialpriceshop.item.domain.Item;
import java.math.BigDecimal;
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
public class TimeDeal extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal timeDealPrice;

    @Embedded
    private TimeDealTimeInfo timeDealTimeInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public TimeDeal(
        final BigDecimal timeDealPrice,
        final LocalDateTime timeDealStartDate,
        final LocalDateTime timeDealEndDate,
        final Item item
    ) {
        this.timeDealPrice = timeDealPrice;
        this.timeDealTimeInfo = new TimeDealTimeInfo(timeDealStartDate, timeDealEndDate);
        this.item = item;
    }

    public boolean isAvailable(final LocalDateTime localDateTime) {
        return localDateTime.isAfter(timeDealTimeInfo.getDealStartDate())
            && localDateTime.isBefore(timeDealTimeInfo.getDealEndDate());
    }

    public BigDecimal calcAmount(final Long stockId, final long quantity) {
        return item.calcAmount(stockId, timeDealPrice, quantity);
    }
}
