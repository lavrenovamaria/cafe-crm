package com.comname.cafecrm.controller

import com.comname.cafecrm.exception.BusinessException
import com.comname.cafecrm.exception.ErrorInfo
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    companion object : KLogging()

    @ExceptionHandler(BusinessException::class)
    fun handle(e: BusinessException) =
        ResponseEntity(
            ErrorInfo(e.message ?: HttpStatus.INTERNAL_SERVER_ERROR.toString()),
            e.status
        ).also { logger.error(e) { e.message } }

    @ExceptionHandler(Exception::class)
    fun handle(e: Exception) =
        ResponseEntity(
            ErrorInfo(e.message ?: HttpStatus.INTERNAL_SERVER_ERROR.toString()),
            HttpStatus.INTERNAL_SERVER_ERROR
        ).also { logger.error(e) { "Unexpected exception occurred: $e" } }

}
