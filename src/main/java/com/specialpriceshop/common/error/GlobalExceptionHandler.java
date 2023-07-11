package com.specialpriceshop.common.error;


import com.specialpriceshop.common.error.exception.BusinessException;
import com.specialpriceshop.common.error.exception.EntityNotFoundException;
import com.specialpriceshop.common.error.exception.InvalidValueException;
import com.specialpriceshop.common.response.ErrorCode;
import com.specialpriceshop.common.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE,
            e.getBindingResult());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
        HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("handleException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
        log.error("handleBusinessException", e);
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(InvalidValueException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final InvalidValueException e) {
        log.error("handleInvalidValueException", e);
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(
        final EntityNotFoundException e) {
        log.error("handleEntityNotFoundException", e);
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @ExceptionHandler(value = BadCredentialsException.class)
    protected ResponseEntity<ErrorResponse> handleBadCredentialsException(
        BadCredentialsException ex) {
        log.error("BadCredentialsException :: ", ex);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.BAD_CREDENTIAL);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(value = MissingRequestCookieException.class)
    protected ResponseEntity<ErrorResponse> handleMissingRequestCookieException(
        MissingRequestCookieException ex
    ) {
        log.error("MissingRequestCookieException ", ex);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.NOT_FOUND_COOKIE);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(value = DisabledException.class)
    protected ResponseEntity<ErrorResponse> handleDisabledException(
        DisabledException ex
    ) {
        log.error("DisabledException ", ex);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.DELETE_USER);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
