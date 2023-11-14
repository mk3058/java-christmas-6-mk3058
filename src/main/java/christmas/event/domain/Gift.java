package christmas.event.domain;

import christmas.user.domain.Order;
import christmas.user.domain.VisitDate;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Gift extends Event {

    private static final String GIFT_NAME = "샴페인";
    private static final BigDecimal BENEFIT_AMOUNT = BigDecimal.valueOf(25_000);
    private static final BigDecimal BENEFIT_THRESHOLD = BigDecimal.valueOf(120_000);

    public Gift() {
        this.eventName = "증정 이벤트";
        this.startAt = LocalDate.of(2023, 12, 1);
        this.endAt = LocalDate.of(2024, 1, 1);
    }

    @Override
    public boolean isEligible(VisitDate visitDate, Order order) {
        if (!hasCorrectAmountAndDate(visitDate, order) || order.getPrice().compareTo(BENEFIT_THRESHOLD) < 0) {
            return false;
        }
        return true;
    }

    @Override
    public BigDecimal getBenefitAmount(VisitDate visitDate, Order order) {
        return BENEFIT_AMOUNT;
    }


}
