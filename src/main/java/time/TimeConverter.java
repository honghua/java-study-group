package time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public final class TimeConverter {
    public static void main(String[] args) {
//        test2();
        test();
    }


    private static void test2() {
        String charSequence = "P1111D";
        Period period = Period.parse(charSequence);
        System.out.println(Period.ofDays(1000).normalized().toString());
        System.out.println(Period.ofMonths(12).plusDays(100).normalized().toString());
    }

    private static void test() {
        CharSequence charSequence = "P36M";
        Period period = Period.parse(charSequence);
        System.out.println(period);

        LocalDate startDate = LocalDate.of(2021, 01, 31);
        LocalDate endDate = LocalDate.of(2021, 02, 28);
//        startDate.plusYears(period.getYears()).plusMonths(period.getMonths()).plusDays(1);
        endDate = startDate.plusMonths(1L);

        System.out.println("start_date: " + startDate);
        System.out.println("start_date: " + endDate);

        System.out.println(
                "********* period between" +
                getPeriod(startDate, endDate) +
                "************"
        );

        LocalDateTime startDateTime = LocalDateTime.of(2021, 06, 30, 1, 0);
        startDateTime.plusMonths(1);

        LocalDate date1 = LocalDate.of(2021,8,30);
        LocalDate date2 = LocalDate.of(2021,9,30);

        System.out.println("hello: " + getPeriod(date1, date2));

        System.out.println(date1.plusMonths(1));
        System.err.println();

        System.err.println(period.toString());
        System.err.println(period.normalized().toString());
        System.err.println(Period.ofDays(1000).normalized().toString());

        long num = 100L;
        System.out.println((int)num);
    }


    public static Period getPeriod(LocalDate startDate, LocalDate endDate) {
        return Period.between(startDate, endDate);
    }

    public static LocalDate getEndDate(LocalDate startDate, Period period) {
        return startDate.plusYears(period.getYears())
                .plusMonths(period.getMonths())
                .plusDays(period.getDays());
    }

}
