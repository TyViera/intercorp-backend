package com.intercorp.backendchallenge.web.advice;

import com.intercorp.backendchallenge.enums.ErrorEnums;
import com.intercorp.backendchallenge.error.ChallengeException;
import com.intercorp.backendchallenge.model.ResponseClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RestControllerAdvice(annotations = RestController.class)
public class ErrorControllerAdvice {

  @ExceptionHandler(ChallengeException.class)
  public ResponseEntity<ResponseClient> handleChallengeException(
      ChallengeException challengeException) {
    log.info("handle challenge exception {} ", challengeException);
    return new ResponseEntity<>(getResponseFromError(challengeException.getError()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseClient> handleException(Exception exception) {
    log.info("handle unexpected error {} ", exception);
    return new ResponseEntity<>(getResponseFromError(ErrorEnums.UNKNOWN),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private ResponseClient getResponseFromError(ErrorEnums error) {
    return new ResponseClient(error.getCode(), error.getMessage());
  }

}
