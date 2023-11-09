package com.specialpriceshop.order.exception;

import com.specialpriceshop.common.error.exception.BusinessException;
import com.specialpriceshop.common.response.ErrorCode;

public class CheckPaymentAmountException extends BusinessException {

    public CheckPaymentAmountException() {
        super(ErrorCode.CHECK_PAYMENT_AMOUNT);
    }
}
