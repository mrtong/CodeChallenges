package com.foo.lorica;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class MainTest {
    private Main mymain;

    @Before
    public void setup() {
        mymain = new Main();
    }

    @Test
    public void shouldReturnFalseWhenAnInvalidYearProvided(){
        final String startDate = "02/06/3000";
        final String endDate = "22/06/1983";

        int expectedDaysDiff = -1;
        int actualDaysDiff = mymain.calculateDayDiff(startDate, endDate);

        assertTrue(actualDaysDiff == expectedDaysDiff);
    }

    @Test
    public void shouldReturnFalseWhenAnInvalidDateFormatProvided(){
        final String startDate = "02";
        final String endDate = "22/06/1983";

        int expectedDaysDiff = -1;
        int actualDaysDiff = mymain.calculateDayDiff(startDate, endDate);

        assertTrue(actualDaysDiff == expectedDaysDiff);
    }

    @Test
    public void shouldReturnFalseWhenPrvoidedDateContainsAChar(){
        final String startDate = "ab/02/1983";
        final String endDate = "22/06/1983";

        int expectedDaysDiff = -1;
        int actualDaysDiff = mymain.calculateDayDiff(startDate, endDate);

        assertTrue(actualDaysDiff == expectedDaysDiff);
    }

    @Test
    public void shouldReturnFalseWhenPrvoidedDateIsEmpty(){
        final String startDate = null;
        final String endDate = "22/06/1983";

        int expectedDaysDiff = -1;
        int actualDaysDiff = mymain.calculateDayDiff(startDate, endDate);

        assertTrue(actualDaysDiff == expectedDaysDiff);
    }

    @Test
    public void shouldReturnFalseWhenDate29ProvidedInANonLeapYear(){
        final String startDate = "29/02/1983";
        final String endDate = "22/06/1983";

        int expectedDaysDiff = -1;
        int actualDaysDiff = mymain.calculateDayDiff(startDate, endDate);

        assertTrue(actualDaysDiff == expectedDaysDiff);
    }

    @Test
    public void shouldReturnCorrectDayDiffWhenDate29ProvidedInALeapYear() throws ParseException {
        final String startDate = "28/02/1983";
        final String endDate = "29/02/2000";

        int expectedDaysDiff = (int) calcalteDaysDiffFromTextPackage(startDate, endDate);;
        int actualDaysDiff = mymain.calculateDayDiff(startDate, endDate);

        assertTrue(actualDaysDiff+1 == expectedDaysDiff);
    }

    @Test
    public void shouldReturnFalseWhenAnInvalidMonthProvided(){
        final String startDate = "02/13/1983";
        final String endDate = "22/06/1983";

        int expectedDaysDiff = -1;
        int actualDaysDiff = mymain.calculateDayDiff(startDate, endDate);

        assertTrue(actualDaysDiff == expectedDaysDiff);
    }

    @Test
    public void shouldReturnCorrectDatesDiffWhenBothEndYearAndStartYearAreTheSame() throws ParseException {
        final String startDate = "02/06/1983";
        final String endDate = "22/06/1983";

        int expectedDaysDiff = (int) calcalteDaysDiffFromTextPackage(startDate, endDate);
        int actualDaysDiff = mymain.calculateDayDiff(startDate, endDate);

        assertTrue(actualDaysDiff+1 == expectedDaysDiff);

    }

    @Test
    public void shouldReturnCorrectDatesDiffEvenIfEndYearAndStartYearAreDifferent() throws ParseException {
        final String startDate = "03/08/1983";
        final String endDate = "03/01/1989";

        int expectedDaysDiff = (int) calcalteDaysDiffFromTextPackage(startDate, endDate);
        int actualDaysDiff = mymain.calculateDayDiff(startDate, endDate);

        assertTrue(actualDaysDiff == expectedDaysDiff);

    }

    private long calcalteDaysDiffFromTextPackage(final String startDate, final String endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date firstDate = sdf.parse(startDate);
        Date secondDate = sdf.parse(endDate);

        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diff;
    }
}