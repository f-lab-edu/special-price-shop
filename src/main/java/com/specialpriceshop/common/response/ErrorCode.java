package com.specialpriceshop.common.response;

import lombok.Getter;

@Getter
public enum ErrorCode {
    //Common
    INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "C003", " Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_TYPE_VALUE(400, "C005", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),

    //TimeDeal
    TIME_DEAL_START_DATE_INVALID(400, "T001", "시작시간과 종료시간이 올바르지 않습니다."),
    TIME_DEAL_NOT_FOUND(404, "T002", "존재하지 않는 타임딜 입니다"),

    //Raffle
    RAFFLE_NOT_FOUND(404, "R001", "존재하지 않는 래플 입니다."),
    RAFFLE_DRAW_DATE_INVALID(400, "R002", "종료시간과 당첨시간이 올바르지 않습니다."),
    RAFFLE_START_DATE_INVALID(400, "R003", "시작시간과 종료시간이 올바르지 않습니다."),
    RAFFLE_PAYMENT_DUE_DATE_INVALID(400, "R004", "종료시간과 결제만료시간이 올바르지 않습니다."),

    //Stock
    STOCK_NOT_FOUND(404, "S001", "존재하지 않는 옵션 입니다.");

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }


}
