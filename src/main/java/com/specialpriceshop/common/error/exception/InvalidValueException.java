package com.specialpriceshop.common.error.exception;

import com.specialpriceshop.common.response.ErrorCode;

public class InvalidValueException extends BusinessException {

    public InvalidValueException(String value) {
        super(value, ErrorCode.INVALID_INPUT_VALUE);
    }

    public InvalidValueException(String value, ErrorCode errorCode) {
        super(value, errorCode);
    }

    public InvalidValueException(final ErrorCode errorCode) {super(errorCode);}
}
