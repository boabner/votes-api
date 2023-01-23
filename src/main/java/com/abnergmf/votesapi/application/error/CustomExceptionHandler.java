package com.abnergmf.votesapi.application.error;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(status, errors);

        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }

    @ExceptionHandler(VoteAPIObjectNotFoundException.class)
    public ResponseEntity<Object> handleVoteAPIObjectNotFoundException(
            VoteAPIObjectNotFoundException exception, WebRequest request) {

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }

    @ExceptionHandler(VotingSesssionClosedOrNotFoundException.class)
    public ResponseEntity<Object> handleSessaoEncerradaException(
            VotingSesssionClosedOrNotFoundException exception, WebRequest request) {

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }

    @ExceptionHandler(PautaAlreadyStartedException.class)
    public ResponseEntity<Object> handleSessaoEncerradaException(
            PautaAlreadyStartedException exception, WebRequest request) {

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }

    @ExceptionHandler(MemberDoubleVoteAttemptException.class)
    public ResponseEntity<Object> handleSessaoEncerradaException(
            MemberDoubleVoteAttemptException exception, WebRequest request) {

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }

    @ExceptionHandler(DuplicateSessionOpenAttemptException.class)
    public ResponseEntity<Object> handleSessaoEncerradaException(
            DuplicateSessionOpenAttemptException exception, WebRequest request) {

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }

}
