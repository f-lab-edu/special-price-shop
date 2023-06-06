package com.specialpriceshop.item.exception;

import com.specialpriceshop.common.error.exception.EntityNotFoundException;
import com.specialpriceshop.common.response.ErrorCode;

public class NotFoundStockException extends EntityNotFoundException {

    public NotFoundStockException() {
        super(ErrorCode.STOCK_NOT_FOUND);
    }
}
