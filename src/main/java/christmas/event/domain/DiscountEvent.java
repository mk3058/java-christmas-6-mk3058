package christmas.event.domain;

import christmas.user.domain.Order;
import christmas.user.domain.VisitDate;
import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class DiscountEvent implements Event {

    protected static final BigDecimal MINIMUM_ORDER_AMOUNT = BigDecimal.valueOf(10_000);

    protected String eventName;
    protected LocalDate startAt;
    protected LocalDate endAt;

    public String getEventName() {
        return eventName;
    }

    protected boolean hasCorrectAmountAndDate(VisitDate visitDate, Order order) {
        if (order.getPrice().compareTo(MINIMUM_ORDER_AMOUNT) < 0) {
            return false;
        }
        if (startAt.isAfter(visitDate.getDate()) || endAt.isBefore(visitDate.getDate())) {
            return false;
        }
        return true;
    }
}
