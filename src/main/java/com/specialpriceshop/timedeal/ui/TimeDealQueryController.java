package com.specialpriceshop.timedeal.ui;

import com.specialpriceshop.timedeal.application.query.TimeDealQueryService;
import com.specialpriceshop.timedeal.dto.TimeDealItemDetailResponse;
import com.specialpriceshop.timedeal.dto.TimeDealItemResponse;
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
@RequestMapping("/time-deals")
@RequiredArgsConstructor
public class TimeDealQueryController {

    private final TimeDealQueryService timeDealQueryService;

    @GetMapping("/{time-deal-id}")
    public ResponseEntity<TimeDealItemDetailResponse> getTimeDealItemDetail(
        @PathVariable("time-deal-id") final Long timeDealId
    ) {
        return ResponseEntity.ok(timeDealQueryService.queryTimeDeal(timeDealId));
    }

    @GetMapping
    public ResponseEntity<Page<TimeDealItemResponse>> getRaffleItemResponseList(
        @RequestParam(name = "page", defaultValue = "0") final int page,
        @RequestParam(name = "size", defaultValue = "10") final int size
    ) {
        return ResponseEntity.ok(
            timeDealQueryService.queryTimeDealList(PageRequest.of(page, size)));
    }
}
