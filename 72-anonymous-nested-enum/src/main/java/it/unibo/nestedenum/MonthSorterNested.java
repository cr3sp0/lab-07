package it.unibo.nestedenum;

import java.util.Comparator;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public enum Month {
        GEN(31, "January"),
        FEB(28, "February"),
        MAR(31, "March"),
        APR(30, "April"),
        MAY(31, "May"),
        JUN(30, "June"),
        JUL(31, "July"),
        AUG(31, "August"),
        SEP(30, "September"),
        OCT(31, "October"),
        NOV(30, "November"),
        DEC(31, "December");

        private final int days;
        private final String actualName;

        private Month(final int days, final String actualName) {
            this.days = days;
            this.actualName = actualName;
        }
    }

    public Month fromString(String requestedMonth) throws IllegalArgumentException {
        int controlCounter = 0;
        Month possibleResult = null;

        for (Month month : Month.values()) {
            if (month.actualName.toUpperCase().contains(requestedMonth.toUpperCase())) {
                controlCounter++;
                possibleResult = month;
            }
        }
        if (controlCounter == 1 && !possibleResult.equals(null)) {
            return possibleResult;
        } else {
            throw new IllegalArgumentException("Exception: no such month!");
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<String>() {
            MonthSorterNested monthList = new MonthSorterNested();

            @Override
            public int compare(String o1, String o2) {
                try {
                    int month1 = monthList.fromString(o1).days;
                    int month2 = monthList.fromString(o2).days;

                    if (month1 > month2) {
                        return 1;
                    } else if (month1 < month2) {
                        return -1;
                    } else {
                        return 0;
                    }

                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return 0;
            }

        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Comparator<String>() {
            MonthSorterNested monthList = new MonthSorterNested();

            @Override
            public int compare(String o1, String o2) {
                try {
                    int month1 = monthList.fromString(o1).ordinal();
                    int month2 = monthList.fromString(o2).ordinal();

                    if (month1 > month2) {
                        return 1;
                    } else if (month1 < month2) {
                        return -1;
                    } else {
                        return 0;
                    }

                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return 0;
            }

        };
    }
}
