package com.specialpriceshop.ui;

import com.specialpriceshop.application.command.RaffleCreateService;
import com.specialpriceshop.dto.RaffleItemCreateRequest;
import java.net.URI;
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
    public ResponseEntity<Void> createRaffleItem(@RequestBody RaffleItemCreateRequest request) {
        final Long createItemId = raffleCreateService.createRaffleItem(request);
        return ResponseEntity
            .created(URI.create("/items/" + createItemId))
            .build();
    }
}
