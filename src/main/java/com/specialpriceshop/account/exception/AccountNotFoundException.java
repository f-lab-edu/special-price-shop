package com.specialpriceshop.account.exception;

import com.specialpriceshop.common.error.exception.EntityNotFoundException;
import com.specialpriceshop.common.response.ErrorCode;

public class AccountNotFoundException extends EntityNotFoundException {

    public AccountNotFoundException() {
        super(ErrorCode.ACCOUNT_NOT_FOUND);
    }
}
