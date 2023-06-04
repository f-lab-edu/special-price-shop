package com.specialpriceshop.common.error.exception;

import com.specialpriceshop.common.response.ErrorCode;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(String message) {
        super(message, ErrorCode.ENTITY_NOT_FOUND);
    }
    public EntityNotFoundException(final String message, final ErrorCode errorCode) {
        super(message, errorCode);
    }
    public EntityNotFoundException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
