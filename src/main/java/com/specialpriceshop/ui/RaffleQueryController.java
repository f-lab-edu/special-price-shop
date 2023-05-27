package com.specialpriceshop.ui;

import com.specialpriceshop.application.query.RaffleQueryService;
import com.specialpriceshop.dto.RaffleItemDetailResponse;
import com.specialpriceshop.dto.RaffleItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/raffles")
@RequiredArgsConstructor
public class RaffleQueryController {

    private final RaffleQueryService raffleQueryService;

    @GetMapping("/{raffle-id}")
    public ResponseEntity<RaffleItemDetailResponse> getRaffleItemDetail(
        @PathVariable("raffle-id") final Long raffleId
    ) {
        return ResponseEntity.ok(raffleQueryService.queryRaffle(raffleId));
    }

    @GetMapping
    public ResponseEntity<Page<RaffleItemResponse>> getRaffleItemResponseList(
        @RequestParam final int page,
        @RequestParam final int size) {
        return ResponseEntity.ok(raffleQueryService.queryRaffleList(PageRequest.of(page, size)));
    }
}
