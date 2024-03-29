package com.specialpriceshop.timedeal.ui;

import com.specialpriceshop.timedeal.application.command.TimeDealCreateService;
import com.specialpriceshop.timedeal.dto.TimeDealItemCreateRequest;
import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time-deals")
@Secured({"ROLE_ADMIN"})
@RequiredArgsConstructor
public class TimeDealItemCreateController {

    private final TimeDealCreateService timeDealCreateService;

    @PostMapping
    public ResponseEntity<Void> createTimeDealItem(
        @RequestBody @Valid TimeDealItemCreateRequest request) {
        final Long createItemId = timeDealCreateService.createTimeDealItem(request);
        return ResponseEntity
            .created(URI.create("/time-deals/" + createItemId))
            .build();
    }
}
