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
    NOT_FOUND_COOKIE(404, "C007", "존재하지 않는 쿠키값 입니다"),

    //TimeDeal
    TIME_DEAL_START_DATE_INVALID(400, "T001", "시작시간과 종료시간이 올바르지 않습니다."),
    TIME_DEAL_NOT_FOUND(404, "T002", "존재하지 않는 타임딜 입니다"),
    TIME_DEAL_NOT_PROGRESSING(400, "T003", "진행중이지 않은 타임딜 입니다"),

    //Raffle
    RAFFLE_NOT_FOUND(404, "R001", "존재하지 않는 래플 입니다."),
    RAFFLE_DRAW_DATE_INVALID(400, "R002", "종료시간과 당첨시간이 올바르지 않습니다."),
    RAFFLE_START_DATE_INVALID(400, "R003", "시작시간과 종료시간이 올바르지 않습니다."),
    RAFFLE_PAYMENT_DUE_DATE_INVALID(400, "R004", "종료시간과 결제만료시간이 올바르지 않습니다."),

    //Stock
    STOCK_NOT_FOUND(404, "S001", "존재하지 않는 옵션 입니다."),
    OUT_OF_STOCK(404, "S002", "주문 가능한 상품의 재고가 모자랍니다."),

    //Account
    EMAIL_DUPLICATED_EXCEPTION(409, "A001", "이미 가입된 이메일 입니다."),
    ACCOUNT_NOT_FOUND(404, "A002", "존재하지 않는 회원입니다."),

    //AUTH,
    UNAUTHORIZED(401, "AUTH001", "잘못된 인증 입니다."),
    BAD_CREDENTIAL(401, "AUTH002", "아이디 혹은 비밀번호가 틀립니다."),
    DELETE_USER(400, "AUTH003", "탈퇴한 사용자 입니다"),

    //ORDER
    NOT_MY_ORDER(400, "ORDER001", "잘못된 주문 입니다."),
    NOT_AVAILABLE_ORDER(400, "ORDER002", "결제한 주문 건 이거나 이미 주문 취소된 결제 입니다."),
    CHECK_PAYMENT_AMOUNT(400, "ORDER003", "주문 금액을 확인하세요."),
    ORDER_NOT_FOUND(404, "ORDER004", "존재하지 않는 주문 입니다"),
    PAYMENT_DUE_DATE_OVER(400, "ORDER005", "결제 만료 시간을 초과 하였습니다."),;

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }


}
