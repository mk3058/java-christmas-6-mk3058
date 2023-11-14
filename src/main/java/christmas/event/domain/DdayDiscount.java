package christmas.event.domain;

import christmas.user.domain.Order;
import christmas.user.domain.VisitDate;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DdayDiscount extends Event {

    private static final BigDecimal INITIAL_DISCOUNT_AMOUNT = BigDecimal.valueOf(1_000);
    private static final BigDecimal DISOUNT_INCREASE_AMOUNT = BigDecimal.valueOf(100);

    public DdayDiscount() {
        this.eventName = "크리스마스 디데이 할인";
        this.startAt = LocalDate.of(2023, 12, 1);
        this.endAt = LocalDate.of(2023, 12, 26);
    }

    @Override
    public boolean isEligible(VisitDate visitDate, Order order) {
        return hasCorrectAmountAndDate(visitDate, order);
    }

    @Override
    public BigDecimal getBenefitAmount(VisitDate visitDate, Order order) {
        BigDecimal increaseAmount = DISOUNT_INCREASE_AMOUNT.multiply(BigDecimal.valueOf(calculateDday(visitDate)));
        return INITIAL_DISCOUNT_AMOUNT.add(increaseAmount);
    }

    private long calculateDday(VisitDate visitDate) {
        return ChronoUnit.DAYS.between(startAt, visitDate.getDate());
    }
}
