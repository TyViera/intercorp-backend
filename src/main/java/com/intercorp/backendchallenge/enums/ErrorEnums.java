package com.intercorp.backendchallenge.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorEnums {

  INSUFFICIENT_DATA("E001", "There are not enough data for calculate values."), //
  NO_DATA("E002", "There are not data in list."), //
  BORN_DATE("E003", "Born date is not valid!"), //
  DEATH_DATE("E004", "Death date is not valid!"), //
  UNKNOWN("E999", "Unknown error.");

  private final String code;
  private final String message;

}
