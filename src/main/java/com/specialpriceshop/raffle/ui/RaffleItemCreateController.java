package com.specialpriceshop.raffle.ui;

import com.specialpriceshop.raffle.application.command.RaffleCreateService;
import com.specialpriceshop.raffle.dto.RaffleItemCreateRequest;
import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/raffles")
@RequiredArgsConstructor
public class RaffleItemCreateController {

    private final RaffleCreateService raffleCreateService;

    @PostMapping
    public ResponseEntity<Void> createRaffleItem(
        @RequestBody @Valid RaffleItemCreateRequest request) {
        final Long createItemId = raffleCreateService.createRaffleItem(request);
        return ResponseEntity
            .created(URI.create("/raffles/" + createItemId))
            .build();
    }
}
