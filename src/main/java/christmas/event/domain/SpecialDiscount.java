package christmas.event.domain;

import christmas.user.domain.Order;
import christmas.user.domain.VisitDate;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDiscount extends DiscountEvent {

    private static final BigDecimal DISCOUNT_AMOUNT = new BigDecimal(1_000);
    private static final LocalDate CHRISTMAS = LocalDate.of(2023, 12, 25);

    public SpecialDiscount() {
        this.eventName = EventName.SPECIAL.getName();
        this.startAt = LocalDate.of(2023, 12, 1);
        this.endAt = LocalDate.of(2024, 1, 1);
    }

    @Override
    public boolean isEligible(VisitDate visitDate, Order order) {
        if (!hasCorrectAmountAndDate(visitDate, order) || !isSundayOrChristmas(visitDate)) {
            return false;
        }
        return true;
    }

    @Override
    public BigDecimal getBenefitAmount(VisitDate visitDate, Order order) {
        return DISCOUNT_AMOUNT;
    }

    private boolean isSundayOrChristmas(VisitDate visitDate) {
        return visitDate.getDayOfWeek() == DayOfWeek.SUNDAY || visitDate.getDate().isEqual(CHRISTMAS);
    }
}
