package com.foo.lorica;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.exit;

public class Main {
    private final Map<Integer, Integer> calendarMap = new HashMap<>();
    private final static int LEAP_YEAR_DAYS = 366;
    private final static int NON_LEAP_YEAR_DAYS = 365;

    public Main() {
        calendarMap.put(1, 31);
        calendarMap.put(3, 31);
        calendarMap.put(5, 31);
        calendarMap.put(7, 31);
        calendarMap.put(8, 31);
        calendarMap.put(10, 31);
        calendarMap.put(12, 31);

        calendarMap.put(2, 28);

        calendarMap.put(4, 30);
        calendarMap.put(6, 30);
        calendarMap.put(9, 30);
        calendarMap.put(11, 30);

    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("You must provide exactly TWO params.");
            exit(0);
        }

        Main mymain = new Main();
        final String startDate = args[0];
        final String endDate = args[1];

        System.out.println(mymain.calculateDayDiff(startDate, endDate));

    }

    private boolean isValidDate(String[] aDate) {
        if (aDate.length != 3) {
            return false;
        }

        int day;
        int month;
        int year;
        try {
            day = new Integer(aDate[0]);
            month = new Integer(aDate[1]);
            year = new Integer(aDate[2]);

        } catch (NumberFormatException e) {
            return false;
        }
        if (year < 1901 || year > 2999) {
            return false;
        }

        if (month > 12 || month < 1) {
            return false;

        }
        boolean leapYear = isLeapYear(year);
        if (leapYear) {
            if (day > 29) {
                return false;
            }
        } else {
            if (day > 28) {

                return false;
            }
        }
        return true;
    }

    private boolean isStringEmpty(Object str) {
        return str == null || "".equals(str);
    }

    public int calculateDayDiff(final String startDate, final String endDate) {
        if (isStringEmpty(startDate)
                || isStringEmpty(endDate)) {
            return -1;
        }

        final String[] startDateArray = startDate.split("/");
        final String[] endDateArray = endDate.split("/");

        if (!isValidDate(startDateArray)
                || !isValidDate(endDateArray)) {
            return -1;
        }

        int dateDiffBetweenStartDateAndItsNewYearDay = calculateDayDiffFromNewYearDay(startDateArray);
        int dateDiffBetweenEndDateAndItsNewYearDay = calculateDayDiffFromNewYearDay(endDateArray);

        int yearOfEndDate = new Integer((endDateArray[2]));
        int yearOfStartDate = new Integer((startDateArray[2]));
        int yearDiffBetweenStartDateAndEndDate = yearOfEndDate - yearOfStartDate;

        int result;

        if (yearDiffBetweenStartDateAndEndDate == 0) {
            result = (Math.abs(dateDiffBetweenEndDateAndItsNewYearDay - dateDiffBetweenStartDateAndItsNewYearDay) - 1);

        } else {
            int totalDaysOfYear = 0;
            for (int i = yearOfStartDate; i < yearOfEndDate; i++) {
                if (isLeapYear(i)) {
                    totalDaysOfYear = totalDaysOfYear + LEAP_YEAR_DAYS;
                } else {
                    totalDaysOfYear = totalDaysOfYear + NON_LEAP_YEAR_DAYS;

                }
            }
            result = dateDiffBetweenEndDateAndItsNewYearDay - dateDiffBetweenStartDateAndItsNewYearDay - 1 + totalDaysOfYear;
        }

        return result;
    }

    private int calculateDayDiffFromNewYearDay(final String[] startDate) {
        final Integer startDay = new Integer(startDate[0]);
        final Integer startMonth = new Integer(startDate[1]);
        final Integer startYear = new Integer(startDate[2]);
        int dateDiffBetweenStartDateAndNewYearDay = startDay;
        for (int i = 1; i < startMonth; i++) {
            if (i == 2) {
                if (isLeapYear(startYear)) {
                    dateDiffBetweenStartDateAndNewYearDay = dateDiffBetweenStartDateAndNewYearDay + 29;

                } else {
                    dateDiffBetweenStartDateAndNewYearDay = dateDiffBetweenStartDateAndNewYearDay + 28;

                }
            } else {

                dateDiffBetweenStartDateAndNewYearDay = dateDiffBetweenStartDateAndNewYearDay + calendarMap.get(i);
            }
        }

        return dateDiffBetweenStartDateAndNewYearDay;
    }

    private static boolean isLeapYear(int year) {
        boolean isLeapYear;

        isLeapYear = (year % 4 == 0);
        isLeapYear = isLeapYear && (year % 100 != 0);
        isLeapYear = isLeapYear || (year % 400 == 0);

        return isLeapYear;
    }
}
