package com.specialpriceshop.repository;

import com.specialpriceshop.domain.Raffle;
import java.util.List;
import java.util.Optional;
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

    @Query(value = "SELECT r FROM Raffle r JOIN FETCH r.item")
    List<Raffle> findAll();
}
