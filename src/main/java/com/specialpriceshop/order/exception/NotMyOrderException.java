package com.specialpriceshop.order.exception;

import com.specialpriceshop.common.error.exception.BusinessException;
import com.specialpriceshop.common.response.ErrorCode;

public class NotMyOrderException extends BusinessException {

    public NotMyOrderException() {
        super(ErrorCode.NOT_MY_ORDER);
    }
}
