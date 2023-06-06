package com.specialpriceshop.timedeal.ui;

import static com.specialpriceshop.fixture.StockCreateRequestFixture.createStockCreateRequest;
import static com.specialpriceshop.fixture.timedeal.TimeDealItemCreateRequestFixture.createTimedealItemCreateRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.specialpriceshop.item.dto.StockCreateRequest;
import com.specialpriceshop.timedeal.application.command.TimeDealCreateService;
import com.specialpriceshop.timedeal.dto.TimeDealItemCreateRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TimeDealItemCreateController.class)
class TimeDealItemCreateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TimeDealCreateService timeDealCreateService;

    @Test
    @DisplayName("새로운 타임딜 상품을 등록한다")
    void createTimeDealItem() throws Exception {

        final TimeDealItemCreateRequest request = createTimedealItemCreateRequest(
            "상품명",
            "상품설명",
            BigDecimal.valueOf(5000),
            BigDecimal.valueOf(1000.0),
            stockFixture());

        mockMvc.perform(post("/time-deals")
                .content(objectMapper.writeValueAsBytes(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(header().exists("Location"));
    }

    @Test
    @DisplayName("상품명이 비어 있을경우")
    void createTimeDealItem_empty_itemName() throws Exception {

        final TimeDealItemCreateRequest request = createTimedealItemCreateRequest(
            null,
            "상품설명",
            BigDecimal.valueOf(5000),
            BigDecimal.valueOf(1000.0),
            stockFixture());

        mockMvc.perform(post("/time-deals")
                .content(objectMapper.writeValueAsBytes(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("C001"))
            .andExpect(jsonPath("$.errors").isArray())
            .andExpect(jsonPath("$.errors").isNotEmpty());
    }

    @Test
    @DisplayName("상품명이 비어 있을경우")
    void createTimeDealItem_empty_itemDescription() throws Exception {

        final TimeDealItemCreateRequest request = createTimedealItemCreateRequest(
            "상품명",
            null,
            BigDecimal.valueOf(5000),
            BigDecimal.valueOf(1000.0),
            stockFixture());

        mockMvc.perform(post("/time-deals")
                .content(objectMapper.writeValueAsBytes(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("C001"))
            .andExpect(jsonPath("$.errors").isArray())
            .andExpect(jsonPath("$.errors").isNotEmpty());
    }

    @Test
    @DisplayName("시작 시간이 과거 일경우")
    void createTimeDealItem_startDate_past() throws Exception {

        final TimeDealItemCreateRequest request = createTimedealItemCreateRequest(
            "상품명",
            "상품설명",
            BigDecimal.valueOf(5000),
            BigDecimal.valueOf(1000.0),
            LocalDateTime.now().minusDays(1L),
            LocalDateTime.now().plusDays(1L),
            stockFixture());

        mockMvc.perform(post("/time-deals")
                .content(objectMapper.writeValueAsBytes(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("C001"))
            .andExpect(jsonPath("$.errors").isArray())
            .andExpect(jsonPath("$.errors").isNotEmpty());
    }

    @Test
    @DisplayName("종료 시간이 과거 일경우")
    void createTimeDealItem_endDate_past() throws Exception {

        final TimeDealItemCreateRequest request = createTimedealItemCreateRequest(
            "상품명",
            "상품설명",
            BigDecimal.valueOf(5000),
            BigDecimal.valueOf(1000.0),
            LocalDateTime.now().plusDays(1L),
            LocalDateTime.now().minusDays(1L),
            stockFixture());

        mockMvc.perform(post("/time-deals")
                .content(objectMapper.writeValueAsBytes(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("C001"))
            .andExpect(jsonPath("$.errors").isArray())
            .andExpect(jsonPath("$.errors").isNotEmpty());
    }

    private List<StockCreateRequest> stockFixture() {
        return List.of(
            createStockCreateRequest("옵션1", 10L, BigDecimal.valueOf(10000.0)),
            createStockCreateRequest("옵션2", 10L, BigDecimal.valueOf(20000.0)),
            createStockCreateRequest("옵션", 10L, BigDecimal.valueOf(30000.0))
        );
    }
}