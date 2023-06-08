package com.specialpriceshop.item.exception;

import com.specialpriceshop.common.error.exception.BusinessException;
import com.specialpriceshop.common.response.ErrorCode;

public class OutOfStockException extends BusinessException {

    public OutOfStockException() {
        super(ErrorCode.OUT_OF_STOCK);
    }

}
