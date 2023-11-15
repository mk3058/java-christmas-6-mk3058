package christmas.event.domain;

import christmas.user.domain.Order;
import christmas.user.domain.VisitDate;
import java.math.BigDecimal;

public interface Event {

    public abstract boolean isEligible(VisitDate visitDate, Order order);

    public abstract BigDecimal getBenefitAmount(VisitDate visitDate, Order order);

    public String getEventName();
}
