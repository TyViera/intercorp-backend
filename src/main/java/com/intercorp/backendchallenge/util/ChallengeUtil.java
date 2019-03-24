package com.intercorp.backendchallenge.util;

import com.intercorp.backendchallenge.enums.ErrorEnums;
import com.intercorp.backendchallenge.error.ChallengeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Component;

@Component
public class ChallengeUtil {

  public Integer getAge(LocalDate bornDate) throws ChallengeException {
    LocalDate now = LocalDate.now();
    if (bornDate == null || bornDate.compareTo(now) > 0) {
      throw new ChallengeException(ErrorEnums.BORN_DATE);
    }
    Period period = Period.between(bornDate, now);
    return period.getYears();
  }

  public LocalDate getDeathDate(LocalDate bornDate, Integer maximunAge) throws ChallengeException {
    Integer age = getAge(bornDate);
    if (age > maximunAge) {
      throw new ChallengeException(ErrorEnums.DEATH_DATE);
    }
    LocalDate lastBirthday = bornDate.plusYears(age);
    LocalDate maximunBirthday = bornDate.plusYears(maximunAge);
    return getRandomDate(lastBirthday, maximunBirthday);
  }

  private LocalDate getRandomDate(LocalDate minDate, LocalDate maxDate) throws ChallengeException {
    long minDay = minDate.toEpochDay();
    long maxDay = maxDate.toEpochDay();
    long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
    return LocalDate.ofEpochDay(randomDay);
  }

}
