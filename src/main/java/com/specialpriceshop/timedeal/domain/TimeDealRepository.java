package com.specialpriceshop.timedeal.domain;

import com.specialpriceshop.timedeal.dto.TimeDealItemResponse;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TimeDealRepository extends JpaRepository<TimeDeal, Long> {

    @Query(value = """
        SELECT t
        FROM TimeDeal t
        INNER JOIN FETCH t.item ti
        LEFT JOIN FETCH ti.stocks
        WHERE t.id =:id
        """)
    Optional<TimeDeal> findById(final Long id);

    @Query(value = """
        SELECT new com.specialpriceshop.timedeal.dto.TimeDealItemResponse(
        t.id,
        t.item.id,
        t.item.name,
        t.item.description,
        t.item.originalPrice,
        t.timeDealPrice,
        t.timeDealTimeInfo.dealStartDate,
        t.timeDealTimeInfo.dealEndDate
        )
        FROM TimeDeal t
        INNER JOIN t.item i
        """)
    Page<TimeDealItemResponse> findAllTimeDealItemResponses(Pageable pageable);

}
