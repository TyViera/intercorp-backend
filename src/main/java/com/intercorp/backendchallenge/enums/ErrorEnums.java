package com.intercorp.backendchallenge.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorEnums {

  UNKNOWN("E001", "Unknown error."), NO_DATA("E002",
      "There are not data in list."), INSUFFICIENT_DATA("E003",
          "There are not sufficient data for calculate values.");

  private final String code;
  private final String message;

}
