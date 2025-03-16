package com.seunghyun.portfolio.admin.advice

import com.seunghyun.portfolio.admin.exception.AdminException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class AdminApiControllerAdvice {

    val log: Logger = LoggerFactory.getLogger(AdminApiControllerAdvice::class.java)

    @ExceptionHandler
    fun handleException(e: AdminException): ResponseEntity<String> {
        log.info(e.message, e)

        return ResponseEntity.status(e.httpStatus).body(e.message)
    }

    @ExceptionHandler
    fun handleException(e: MethodArgumentNotValidException): ResponseEntity<String> {
        log.info(e.message, e)

        // 어떤 검증에서 오류가 발생했는지 정보를 가지고 있다.
        val fieldError = e.bindingResult.fieldErrors[0]
        val message = "[${fieldError.field}] ${fieldError.defaultMessage}]"

        return ResponseEntity.badRequest().body(message)
    }

    @ExceptionHandler
    fun handleException(e: Exception): ResponseEntity<String> {
        log.info(e.message, e)

        return ResponseEntity.internalServerError().body("시스템에 오류가 발생했어요.")
    }
}