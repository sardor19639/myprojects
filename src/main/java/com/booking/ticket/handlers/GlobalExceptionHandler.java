/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booking.ticket.handlers;

import com.fasterxml.jackson.core.JsonParseException;
import com.booking.ticket.enums.Enums;
import com.booking.ticket.logger.LogMessage;

import com.booking.ticket.exceptions.AccessDeniedException;
import com.booking.ticket.exceptions.InvalidUserInputException;
import com.booking.ticket.exceptions.NoSuchElementInDBException;
import com.booking.ticket.exceptions.RequestLimitException;
import com.booking.ticket.exceptions.UniqueViolationException;
import com.booking.ticket.exceptions.UnknownException;
import com.booking.ticket.logger.BookingTicketLogger;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author sardorrokhillayev
 */
@ControllerAdvice
@EnableWebMvc
public class GlobalExceptionHandler {

    BookingTicketLogger logger = BookingTicketLogger.getInstance();
    @Autowired
    
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(value = JsonSyntaxException.class)
    public ResponseEntity<String> jsonFormatException(JsonSyntaxException e) {
        e.printStackTrace();
        logger.simpleLog("Wrong jsonFormat");
        final JsonObject resJO = new JsonObject();
        resJO.addProperty("error", 4);
        resJO.addProperty("message", "Wrong json format");
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                body(resJO.toString());
    }

    //ResourceNotFoundException
    @ExceptionHandler(value = MissingPathVariableException.class)
    public ResponseEntity<String> pathVariableMissingException(MissingPathVariableException e) {
        logger.simpleLog("Missed request params");
        final JsonObject resJO = new JsonObject();
        resJO.addProperty("error", 4);
        resJO.addProperty("message", "Missed request param: "+e.getParameter().getParameterName());

        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                body(resJO.toString());
    }
    
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> wrongHttpMethod(HttpRequestMethodNotSupportedException e) {
        logger.simpleLog("Wrong http method");
        final JsonObject resJO = new JsonObject();
        resJO.addProperty("error", 4);
        resJO.addProperty("message", "Unsupported http method");
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                body(resJO.toString());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<String> missingRequestParams(MissingServletRequestParameterException e) {
        logger.simpleLog("Missed request params");
        final JsonObject resJO = new JsonObject();
        resJO.addProperty("error", 4);
        resJO.addProperty("message", "Missed request param: "+e.getParameterName());
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                body(resJO.toString());
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<String> handleResourceNotFoundException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        violations.forEach((violation) -> {
            strBuilder.append(violation.getMessage())
                      .append("\n");
        });
        logger.simpleLog("Wrong request params: " + strBuilder);
        final JsonObject resJO = new JsonObject();
        resJO.addProperty("error", 4);
        resJO.addProperty("message", "Wrong req params: "+strBuilder);
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                body(resJO.toString());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> validationException(MethodArgumentNotValidException e) {
        LogMessage message = LogMessage.newBuilder()
                .setClassName(e.getStackTrace()[0].getClassName())
                .setMethodName(e.getStackTrace()[0].getMethodName())
                .setErrorCode(Enums.Error.WRONG_INPUT.toString())
                .setRequestBody(e.getParameter().getParameterName())
                .setCause(e.getBindingResult().getFieldError().getDefaultMessage())
                .build();
        final JsonObject resJO = new JsonObject();
        resJO.addProperty("error", 4);
        resJO.addProperty("message", e.getBindingResult().getFieldError().getDefaultMessage());
        logger.log(message);
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                body(resJO.toString());
    }

    @ExceptionHandler(value = JsonParseException.class)
    public ResponseEntity<String> jsonParseException(JsonSyntaxException e) {
        e.printStackTrace();
        logger.simpleLog("Wrong jsonFormat");
        final JsonObject resJO = new JsonObject();
        resJO.addProperty("error", 4);
        resJO.addProperty("message", e.getMessage());
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                body(resJO.toString());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<String> messageNotReadableExc(HttpMessageNotReadableException e) {
        e.printStackTrace();
        logger.simpleLog("Wrong jsonFormat");
        final JsonObject resJO = new JsonObject();
        resJO.addProperty("error", 4);
        resJO.addProperty("message", e.getMessage());
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                body(resJO.toString());
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> wrongUrlExc(MethodArgumentTypeMismatchException e) {
        logger.simpleLog("Wrong url format");
        final JsonObject resJO = new JsonObject();
        resJO.addProperty("error", 4);
        resJO.addProperty("message", e.getMessage());
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                body(resJO.toString());
    }

    @ExceptionHandler(value = InvalidUserInputException.class)
    public ResponseEntity<String> invalidUserInputExc(InvalidUserInputException e) {
        final JsonObject responseJO = new JsonObject();
        responseJO.addProperty("error", 4);
        responseJO.addProperty("message", e.message().getCause());
        logger.errorLog(e);
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                body(responseJO.toString());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<String> accessDeniedException(AccessDeniedException e) {
        logger.errorLog(e);
        final JsonObject responseJO = new JsonObject();
        responseJO.addProperty("error",2 );
        responseJO.addProperty("message", e.message().getCause());
        return ResponseEntity.
                status(HttpStatus.CONFLICT).
                body(responseJO.toString());
    }

    @ExceptionHandler(value = NoSuchElementInDBException.class)
    public ResponseEntity<String> noSuchElemInDBExc(NoSuchElementInDBException e) {
        logger.errorLog(e);
        final JsonObject responseJO = new JsonObject();
        responseJO.addProperty("error",5 );
        responseJO.addProperty("message", e.message().getCause());
        return ResponseEntity.
                status(HttpStatus.NOT_FOUND).
                body(responseJO.toString());
    }

    @ExceptionHandler(value = UnknownException.class)
    public ResponseEntity<String> unknownExc(UnknownException e) {
        logger.errorLog(e);
        final JsonObject responseJO = new JsonObject();
        responseJO.addProperty("error",10 );
        responseJO.addProperty("message", e.message().getCause());
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(responseJO.toString());
    }

    @ExceptionHandler(value = RequestLimitException.class)
    public ResponseEntity<String> requetLimitExc(RequestLimitException e) {
        logger.errorLog(e);
        final JsonObject responseJO = new JsonObject();
        responseJO.addProperty("error",30);
        responseJO.addProperty("message", e.message().getCause());
        return ResponseEntity.
                status(HttpStatus.TOO_MANY_REQUESTS).
                body(responseJO.toString());
    }

    @ExceptionHandler(value = UniqueViolationException.class)
    public ResponseEntity<String> uniqueViolExc(UniqueViolationException e) {
        logger.errorLog(e);
        final JsonObject responseJO = new JsonObject();
        responseJO.addProperty("error",21);
        responseJO.addProperty("message", e.message().getCause());
        return ResponseEntity.
                status(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS).
                body(responseJO.toString());
    }

    @ExceptionHandler(SocketTimeoutException.class)
    public ResponseEntity<String> socketTimeOutExc(SocketTimeoutException exc) {
        logger.simpleLog("SOcket Time out exc");
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(Enums.Error.UNKNOWN_ERROR.toString());
    }
    
    @ExceptionHandler(SocketException.class)
    public ResponseEntity<String> socketTimeOutExc(SocketException exc) {
        logger.simpleLog("Socket  exc");
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(Enums.Error.UNKNOWN_ERROR.toString());
    }

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<String> timeOutExc(TimeoutException exc) {
        logger.simpleLog("Time out exception");
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(Enums.Error.UNKNOWN_ERROR.toString());
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<String> connectException(ConnectException exc) {
        logger.simpleLog("Connect exception");
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(Enums.Error.UNKNOWN_ERROR.toString());
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<String> databaseError(PSQLException exc) {
        logger.simpleLog("Unknown db exception: "+exc.getMessage());

        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(Enums.Error.UNKNOWN_ERROR.toString());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> unknownError(Exception exc) {
        StringWriter errors = new StringWriter();
        exc.printStackTrace(new PrintWriter(errors));
        final String errorMessage = errors.toString() + "\n"
                + "time: " + new Date();
        final JsonObject resJO = new JsonObject();
        resJO.addProperty("error", 10);
        resJO.addProperty("message", exc.getMessage());
        
        log.error("Error",exc);
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(resJO.toString());
    }

}
