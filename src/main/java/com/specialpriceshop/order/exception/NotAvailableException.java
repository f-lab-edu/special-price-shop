package com.specialpriceshop.order.exception;

import com.specialpriceshop.common.error.exception.BusinessException;
import com.specialpriceshop.common.response.ErrorCode;

public class NotAvailableException extends BusinessException {

    public NotAvailableException() {
        super(ErrorCode.NOT_AVAILABLE_ORDER);
    }
}
