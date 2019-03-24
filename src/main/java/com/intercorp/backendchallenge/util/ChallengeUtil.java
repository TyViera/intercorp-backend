package com.intercorp.backendchallenge.util;

import java.time.LocalDate;
import java.time.Period;
import org.springframework.stereotype.Component;

@Component
public class ChallengeUtil {

  public Integer getAge(LocalDate birthday) {
    Period period = Period.between(birthday, LocalDate.now());
    return period.getYears();
  }

}
