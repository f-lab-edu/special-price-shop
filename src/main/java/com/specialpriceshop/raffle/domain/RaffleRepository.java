package com.specialpriceshop.raffle.domain;

import com.specialpriceshop.raffle.dto.RaffleItemResponse;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RaffleRepository extends JpaRepository<Raffle, Long> {

    @Query(value = """
        SELECT r
        FROM Raffle r
        INNER JOIN FETCH r.item ri
        LEFT JOIN FETCH ri.stocks
        WHERE r.id =:id""")
    Optional<Raffle> findById(final Long id);

    @Query(value = """
        SELECT new com.specialpriceshop.raffle.dto.RaffleItemResponse(
                r.id,
                i.id,
                i.name,
                i.description,
                i.originalPrice,
                r.rafflePrice,
                r.raffleTimeInfo.raffleStartDate,
                r.raffleTimeInfo.raffleEndDate,
                r.raffleTimeInfo.drawDate,
                r.raffleTimeInfo.paymentDueDate
        )
        FROM Raffle r
        INNER JOIN r.item i
        """)
    Page<RaffleItemResponse> findAllRaffleItemResponses(Pageable pageable);
}
