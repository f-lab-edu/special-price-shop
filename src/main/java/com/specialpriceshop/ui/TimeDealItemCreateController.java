package com.specialpriceshop.ui;

import com.specialpriceshop.application.command.TimeDealCreateService;
import com.specialpriceshop.dto.TimeDealItemCreateRequest;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time-deals")
@RequiredArgsConstructor
public class TimeDealItemCreateController {

    private final TimeDealCreateService timeDealCreateService;

    @PostMapping
    public ResponseEntity<Void> createTimeDealItem(@RequestBody TimeDealItemCreateRequest request) {
        final Long createItemId = timeDealCreateService.createTimeDealItem(request);
        return ResponseEntity
            .created(URI.create("/time-deals/" + createItemId))
            .build();
    }
}
