package src;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * This it the pack.pack.src.Date class that stores information about the release date of an album
 * <p>
 * This includes: year, month, and day
 *
 * @author Divyesh Nemam Baskaran, Viraj Patel
 */
public class Date implements Comparable<Date> {

    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int THE_EIGHTYS = 1980;
    public static final int MAX_DAYS = 31;
    public static final int MAX_MONTH = 12;
    public static final int MIN_MONTH = 1;
    public static final int FEBRUARY = 2;

    /**
     * This constructor builds a pack.pack.src.Date object with the date specified by an input String.
     *
     * @param date, String variable that is in the form of mm/dd/yyyy
     */
    public Date(String date) {
        StringTokenizer st = new StringTokenizer(date, "/");
        this.month = Integer.parseInt(st.nextToken());
        this.day = Integer.parseInt(st.nextToken());
        this.year = Integer.parseInt(st.nextToken());
    }

    /**
     * This constructor builds a pack.pack.src.Date object with the Today's current date
     */
    public Date() {
        Calendar current = Calendar.getInstance();
        this.year = current.get(Calendar.YEAR);
        this.month = current.get(Calendar.MONTH) + 1;
        this.day = current.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Returns the day of the pack.pack.src.Date object
     *
     * @return this.day
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Returns the month of the pack.pack.src.Date object
     *
     * @return this.month
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Returns the year of the pack.pack.src.Date object
     *
     * @return this.year
     */
    public int getYear() {
        return this.year;
    }

    /**
     * This method checks if the the date is valid based on calendar rules.
     * <p>
     * Dates should be after the start of 1980 and before Today's date
     * The month value should be between 1-12
     * The date value should be between 1-(that months max days)
     * The months max days also accounts for leap years.
     *
     * @return True, if the date is valid
     * False, if the date is not valid
     */
    public boolean isValid() {
        boolean isAfter1980 = after1980();
        boolean isBeforeToday = beforeToday();
        boolean isMonthAndDayValid = monthAndDayValidator();

        return (isAfter1980 && isBeforeToday) && isMonthAndDayValid;
    }

    /**
     * Checks the year to make sure it is 1980 or later
     *
     * @return True if the year is 1980 or later,
     * False if the year is before 1980.
     */
    private boolean after1980() {
        return this.year >= THE_EIGHTYS;
    }

    /**
     * Checks the input pack.pack.src.Date object to see if it is before today's date
     *
     * @return True, if the date is before or equal to today's date
     * False, if the date is after today's date
     */
    private boolean beforeToday() {
        Date today = new Date();
        if (this.year > today.year){
            return false;
        } if (this.year == today.year) {
            if (this.month > today.month) {
                return false;
            } if (this.month == today.month){
                if (this.day > today.day){
                    return false;
                } else return true;
            } else return true;
        } else return true;
    }



    /**
     * Checks if the year is a leap year or not
     *
     * @return True, if the year is a leap year
     * False, if the year is not a leap year
     */
    private boolean isLeapYear() {
        if (this.year % QUADRENNIAL == 0) {
            if (this.year % CENTENNIAL == 0) {
                return this.year % QUATERCENTENNIAL == 0;
            } else return true;
        } else return false;
    }

    /**
     * This method checks if the input Month and Day are valid numbers
     * @return
     */
    private boolean monthAndDayValidator() {
        if (this.month < MIN_MONTH || this.month > MAX_MONTH || this.day <= 0) {
            return false;
        }
        if(this.day <= getMaxDaysInMonth()) {
            return true;
        }else return false;
    }

    /**
     * This method returns the max days there should be in this.month
     * @return maxDays - integer value of the max days in this.month
     */
    private int getMaxDaysInMonth() {
        int maxDays;
        maxDays = switch (this.month) {
            case Calendar.JANUARY + 1, Calendar.MARCH + 1, Calendar.MAY + 1, Calendar.JULY + 1, Calendar.AUGUST + 1,
                    Calendar.OCTOBER + 1, Calendar.DECEMBER + 1 -> MAX_DAYS;
            case Calendar.APRIL + 1, Calendar.JUNE + 1, Calendar.SEPTEMBER + 1, Calendar.NOVEMBER + 1 -> MAX_DAYS - 1;
            default -> 0;
        };
        if (this.month == FEBRUARY) {
            if (isLeapYear()) {
                maxDays = MAX_DAYS - FEBRUARY;
            } else maxDays = MAX_DAYS - FEBRUARY - MIN_MONTH;
        }
        return maxDays;
    }

    /**
     * Compares this pack.pack.src.Date object with another pack.pack.src.Date object to check if they are the same date
     *
     * @param date, pack.pack.src.Date object to compare to existing pack.pack.src.Date object
     * @return 1, if the dates are the same
     * 0, if the dates are different
     */
    //@Override
    public int compareTo(Date date) {
        if (this.day == date.getDay() && this.month == date.getMonth() && this.year == date.getYear()) {
            return 1;
        } else return 0;
    }

    /**
     * Returns the date stored in the date object in the form of
     * mm/dd/yyyy
     *
     * @return String representing the date object
     */
    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    /**
     * Testbed main to test the isValid() method in the pack.pack.src.Date class
     */
    public static void main (String [] args) {
        //Testing isValid() Method

        //Test Case #1
        System.out.println("---- Test Case 1 ----");
        Date test1 = new Date ("13/1/1999");
        boolean expectedValue1 = false;
        if(!test1.isValid()) System.out.println("Test passed.");
        else System.out.println("Test failed.");

        System.out.println("Running Test Case#1b");
        Date tCase1b = new Date("12/1/2000");
        if(tCase1b.isValid()) System.out.println("Test Case#1b, checking a date with valid month. Passed");
        else System.out.println("Test Case#1b, checking a date with valid month. Failed");

        // Test Case #2, checking 02/29 on a non-leap year.
        System.out.println("Running Test Case#2");
        Date tCase2a = new Date("2/29/2019");
        if(!tCase2a.isValid()) System.out.println("Test Case#2a, checking a date with 02/29 on a non-leap year. Passed");
        else System.out.println("Test Case#2a, checking a date 02/29 on a non-leap year. Failed");

        System.out.println("Running Test Case#2b");
        Date tCase2b = new Date("2/29/2020");
        if(tCase2b.isValid()) System.out.println("Test Case#2b, checking a date with 02/29 on a leap year. Passed");
        else System.out.println("Test Case#2b, checking a date with 02/29 on a leap year. Failed");

        // Test Case #3, checking a date before 1980.
        System.out.println("Running Test Case#3a");
        Date tCase3a = new Date("3/31/1800");
        if(!tCase3a.isValid()) System.out.println("Test Case#3a, checking a date before 1980. Passed");
        else System.out.println("Test Case#3a, checking a date before 1980. Failed");

        System.out.println("Running Test Case#3b");
        Date tCase3b = new Date("3/31/1980");
        if(tCase3b.isValid()) System.out.println("Test Case#3b, checking a date after 1980. Passed");
        else System.out.println("Test Case#3b, checking a date after 1980. Failed");

        // Test Case #4, checking invalid day.
        System.out.println("Running Test Case#4a");
        Date tCase4a = new Date("4/-15/2009");
        if(!tCase4a.isValid()) System.out.println("Test Case#4a, checking a date with invalid day. Passed");
        else System.out.println("Test Case#4a, checking a date with invalid day. Failed");

        System.out.println("Running Test Case#4b");
        Date tCase4b = new Date("4/15/2009");
        if(tCase4b.isValid()) System.out.println("Test Case#4b, checking a date with valid day. Passed");
        else System.out.println("Test Case#4b, checking a date with valid day. Failed");

        // Test Case #5, testing day=31 on a 30-day month.
        System.out.println("Running Test Case#5a");
        Date tCase5a = new Date("4/31/2009");
        if(!tCase5a.isValid()) System.out.println("Test Case#5a, testing day=31 on a 30-day month. Passed");
        else System.out.println("Test Case#5a,testing day=31 on a 30-day month. Failed");

        System.out.println("Running Test Case#5b");
        Date tCase5b = new Date("5/31/2009");
        if(tCase5b.isValid()) System.out.println("Test Case#5b, testing day=31 on a 31-day month. Passed");
        else System.out.println("Test Case#5b, testing day=31 on a 31-day month. Failed");

        // Test Case#6, checking a date in the future.
        System.out.println("Running Test Case#6a");
        Date tCase6a = new Date("4/30/2109");
        if(!tCase6a.isValid()) System.out.println("Test Case#6a, checking a date in the future. Passed");
        else System.out.println("Test Case#6a, checking a date in the future. Failed");

        System.out.println("Running Test Case#6b");
        Date tCase6b = new Date("4/30/1989");
        if(tCase6b.isValid()) System.out.println("Test Case#6b, checking a date that has occurred, but after 1980. Passed");
        else System.out.println("Test Case#6b, checking a date that has occurred, but after 1980. Failed");

        // Test Case#7, checking a correct date.
        System.out.println("Running Test Case#7");
        Date tCase7 = new Date("4/30/2019");
        if(tCase7.isValid()) System.out.println("Test Case#7, checking a normal date. Passed");
        else System.out.println("Test Case#7, checking a normal date. Failed");

        //End
        System.out.println("\nTesting Completed ");
    }
}
