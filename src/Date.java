import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * A date class that allows for object representation of a date in string form
 * @author Rory Xu, Hassan Alfareed
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public Date(String date) {
        StringTokenizer st = new StringTokenizer(date);
        month = Integer.parseInt(st.nextToken("/")) - 1;
        day = Integer.parseInt(st.nextToken("/"));
        year = Integer.parseInt(st.nextToken("/"));

    }

    public Date() {
        Calendar present = Calendar.getInstance();
        this.month = present.get(Calendar.MONTH);
        this.day = present.get(Calendar.DATE);
        this.year = present.get(Calendar.YEAR);
    }

    @Override
    public int compareTo(Date date) {
        if (year != date.year) {
            return Integer.compare(year, date.year);
        }
        if (month != date.month) {
            return Integer.compare(month, date.month);
        }
        return Integer.compare(day, date.day);
    }

    @Override
    public String toString() {
        return month+1 + "/" + day + "/" + year;
    }

    public boolean isValid() {

        Date current = new Date();
        int standardMonthDays = 30;
        int extendedMonthDays = 31;
        int nonLeapFebDays = 28;
        int leapFebDays = 29;

        if (year < 1900 || month < 0 || month > 11) return false;

        if (isThirtyOne(this.month) && (this.day > extendedMonthDays)) {
            return false;
        }

        if (isThirty(this.month) && (this.day > standardMonthDays)) {
            return false;
        }

        if (month == Calendar.FEBRUARY && isLeapYr(this.year) && this.day > leapFebDays) {
            return false;
        }

        return month != Calendar.FEBRUARY || isLeapYr(this.year) || this.day <= nonLeapFebDays;
    }

    public static boolean inArray(int[] arr, int valueToCheck) {
        boolean result = false;
        for (int month : arr) {
            if (month == valueToCheck) {
                result = true;
                return result;
            }
        }
        return result;
    }

    public static boolean isLeapYr(int year) {
        final int QUADRENNIAL = 4;
        final int CENTENNIAL = 100;
        final int QUATERCENTENNIAL = 400;
        return ((year % QUADRENNIAL == 0) && (year % CENTENNIAL != 0)) || (year % QUATERCENTENNIAL == 0);
    }

    public static boolean isThirtyOne(int month) {
        int[] months = {0, 2, 4, 6, 7, 9, 11};
        return (inArray(months, month));
    }

    public static boolean isThirty(int month) {
        int[] months = {3, 5, 8, 10};
        return (inArray(months, month));
    }

    public int getYear() {
        return year;
    }

    public static void testbed(String args[]) {
        Date d1 = new Date("02/27/1899");           //testcase#1
        Date d2 = new Date("02/29/2001");           //testcase#2
        Date d3 = new Date("00/29/2001");           //testcase#3
        Date d4 = new Date("13/13/2022");           //testcase#4
        Date d5 = new Date();                       //testcase#5

        System.out.println(d1.isValid());
        System.out.println(d2.isValid());
        System.out.println(d3.isValid());
        System.out.println(d4.isValid());
        System.out.println(d5.isValid());
        //System.out.println(d1.month);
        //System.out.println(isLeapYr(d1.year));
    }
}
