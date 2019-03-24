package com.intercorp.backendchallenge.error;

import com.intercorp.backendchallenge.enums.ErrorEnums;
import lombok.Getter;

@Getter
public class ChallengeException extends RuntimeException {

  private static final long serialVersionUID = 808308342277631087L;

  private ErrorEnums error;

  public ChallengeException(ErrorEnums error) {
    super(error.getMessage());
    this.error = error;
  }

}
