package com.intercorp.backendchallenge.util;

import com.intercorp.backendchallenge.error.ChallengeException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class ChallengeUtilTest {

  @InjectMocks
  private ChallengeUtil challengeUtil;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test(expected = ChallengeException.class)
  public void getFutureAgeTest() throws Exception {
    LocalDate bornDate = LocalDate.now().plusYears(13);
    challengeUtil.getAge(bornDate);
  }

  @Test(expected = ChallengeException.class)
  public void getAgeNullTest() throws Exception {
    challengeUtil.getAge(null);
  }

  @Test
  @PrepareForTest(LocalDate.class)
  public void getAgeTest() throws Exception {
    // given
    PowerMockito.mockStatic(LocalDate.class);
    Mockito.when(LocalDate.now()).thenReturn(getTodayForTest());

    // when
    LocalDate bornDate = getDateForTest(2000, GregorianCalendar.DECEMBER, 12);
    Integer age = challengeUtil.getAge(bornDate);

    // then
    Integer expectedAge = 18;
    Assert.assertNotNull("Result age was null!", age);
    Assert.assertEquals("Result age was not expected value!", expectedAge, age);
  }

  //
  @Test(expected = ChallengeException.class)
  public void getDeathDateNullTest() throws Exception {
    challengeUtil.getDeathDate(null, 80);
  }

  @Test(expected = ChallengeException.class)
  public void getDeathDateWorngMaxAgeTest() throws Exception {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate bornDate = LocalDate.parse("2000-12-12", format);
    challengeUtil.getDeathDate(bornDate, 10);
  }

  @Test
  @PrepareForTest(LocalDate.class)
  public void getDeathDateTest() throws Exception {
    // given
    LocalDate now = getTodayForTest();
    PowerMockito.mockStatic(LocalDate.class);
    Mockito.when(LocalDate.now()).thenReturn(now);

    // when
    Integer maxAge = 80;
    LocalDate bornDate = getDateForTest(2000, GregorianCalendar.DECEMBER, 12);
    LocalDate maxDate = bornDate.plusYears(maxAge);

    LocalDate deathAge = challengeUtil.getDeathDate(bornDate, maxAge);

    // then
    Assert.assertNotNull("Result age was null!", deathAge);
    MatcherAssert.assertThat("Death age was less than now", deathAge, Matchers.greaterThan(now));
    MatcherAssert.assertThat("Death age was greater than max death date", deathAge,
        Matchers.lessThan(maxDate));
  }

  private LocalDate getTodayForTest() {
    return getDateForTest(2019, GregorianCalendar.MARCH, 24);
  }

  private LocalDate getDateForTest(Integer year, Integer month, Integer day) {
    Calendar calendar = GregorianCalendar.getInstance();
    calendar.set(GregorianCalendar.YEAR, year);
    calendar.set(GregorianCalendar.MONTH, month);
    calendar.set(GregorianCalendar.DATE, day);
    calendar.set(GregorianCalendar.HOUR, 0);
    calendar.set(GregorianCalendar.MINUTE, 0);
    calendar.set(GregorianCalendar.SECOND, 0);
    calendar.set(GregorianCalendar.MILLISECOND, 0);
    return calendar.toInstant().atZone(ZoneOffset.UTC).toLocalDate();
  }

}
