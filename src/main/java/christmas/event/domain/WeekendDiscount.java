package christmas.event.domain;

import christmas.menu.domain.MenuCategory;
import christmas.user.domain.Order;
import christmas.user.domain.VisitDate;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map.Entry;

public class WeekendDiscount extends DiscountEvent {

    private static final BigDecimal DISCOUNT_AMOUNT_PER_MAIN = BigDecimal.valueOf(2_023);

    public WeekendDiscount() {
        this.eventName = "주말 할인";
        this.startAt = LocalDate.of(2023, 12, 1);
        this.endAt = LocalDate.of(2024, 1, 1);
    }

    @Override
    public boolean isEligible(VisitDate visitDate, Order order) {
        if (!hasCorrectAmountAndDate(visitDate, order) || countMainMenu(order) < 1 || !visitDate.isWeekend()) {
            return false;
        }
        return true;
    }

    @Override
    public BigDecimal getBenefitAmount(VisitDate visitDate, Order order) {
        return DISCOUNT_AMOUNT_PER_MAIN.multiply(BigDecimal.valueOf(countMainMenu(order)));
    }

    private int countMainMenu(Order order) {
        return order.getMenus().entrySet().stream()
                .filter(entry -> MenuCategory.MAIN.menus().contains(entry.getKey()))
                .mapToInt(Entry::getValue)
                .sum();
    }
}
