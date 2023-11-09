package com.specialpriceshop.order.exception;

import com.specialpriceshop.common.error.exception.BusinessException;
import com.specialpriceshop.common.response.ErrorCode;

public class PaymentDueDateOverException extends BusinessException {

    public PaymentDueDateOverException() {
        super(ErrorCode.PAYMENT_DUE_DATE_OVER);
    }
}
