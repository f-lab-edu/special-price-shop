package com.specialpriceshop.ui;

import com.specialpriceshop.application.query.RaffleQueryService;
import com.specialpriceshop.dto.RaffleItemDetailResponse;
import com.specialpriceshop.dto.RaffleItemResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/raffles")
@RequiredArgsConstructor
public class RaffleQueryController {

    private final RaffleQueryService raffleQueryService;

    @GetMapping("/{raffleId}")
    public ResponseEntity<RaffleItemDetailResponse> getRaffleItemDetail(
        @PathVariable("raffleId") final Long raffleId
    ) {
        return ResponseEntity.ok(raffleQueryService.queryRaffle(raffleId));
    }

    @GetMapping
    public ResponseEntity<List<RaffleItemResponse>> getRaffleItemResponseList() {
        return ResponseEntity.ok(raffleQueryService.queryRaffleList());
    }
}
