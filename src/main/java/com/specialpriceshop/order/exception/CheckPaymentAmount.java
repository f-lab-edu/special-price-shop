package com.specialpriceshop.order.exception;

import com.specialpriceshop.common.error.exception.BusinessException;
import com.specialpriceshop.common.response.ErrorCode;

public class CheckPaymentAmount extends BusinessException {

    public CheckPaymentAmount() {
        super(ErrorCode.CHECK_PAYMENT_AMOUNT);
    }
}
