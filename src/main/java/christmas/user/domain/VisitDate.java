package christmas.user.domain;

import christmas.message.Error;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitDate {

    private static final int MINIMUM_VISIT_DAY = 1;
    private static final int MAXIMUM_VISIT_DAY = 31;
    private static final int YEAR = 2023;
    private static final int MONTH = 12;

    private final LocalDate date;

    public VisitDate(int date) {
        validate(date);
        this.date = LocalDate.of(YEAR, MONTH, date);
    }

    public DayOfWeek getDayOfWeek() {
        return date.getDayOfWeek();
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isWeekend() {
        return date.getDayOfWeek().getValue() > DayOfWeek.FRIDAY.getValue();
    }

    private void validate(int date) {
        if (!hasCorrectRange(date, MINIMUM_VISIT_DAY, MAXIMUM_VISIT_DAY)) {
            throw new IllegalArgumentException(Error.INVALUD_DATE.toString());
        }
    }

    private boolean hasCorrectRange(int num, int min, int max) {
        return min <= num && num <= max;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        VisitDate object = (VisitDate) obj;
        return date.equals(object.date);
    }
}
