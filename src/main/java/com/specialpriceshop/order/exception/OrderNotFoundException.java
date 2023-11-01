package com.specialpriceshop.order.exception;

import com.specialpriceshop.common.error.exception.EntityNotFoundException;
import com.specialpriceshop.common.response.ErrorCode;

public class OrderNotFoundException extends EntityNotFoundException {

    public OrderNotFoundException(final String target) {
        super(target + " is Not Found", ErrorCode.ORDER_NOT_FOUND);
    }

    public OrderNotFoundException() {
        super(ErrorCode.ORDER_NOT_FOUND);
    }
}
