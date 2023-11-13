package christmas.user.domain;

import christmas.menu.domain.MenuCategory;
import christmas.menu.domain.MenuItem;
import christmas.message.Error;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Order {

    private static final Integer MAXIMUM_PURCHASE_COUNT = 20;

    private final Map<MenuItem, Integer> menus;

    public Order(Map<MenuItem, Integer> menus) {
        validate(menus);
        this.menus = Collections.unmodifiableMap(menus);
    }

    public BigDecimal getPrice() {
        return menus.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void validate(Map<MenuItem, Integer> input) {
        if (!isOrderQuantityPositive(input) || hasDuplicate(input)) {
            throw new IllegalArgumentException(Error.INVALUD_ORDER.toString());
        }
        if (!isOrderQuantityWithinLimit(input)) {
            throw new IllegalArgumentException(Error.MAXIMUM_ORDER_COUNT_EXCEEDED.toString());
        }
        if (isComposedOfBeverage(input)) {
            throw new IllegalArgumentException(Error.ORDER_ITEM_COMPOSED_OF_BEVERAGE.toString());
        }
    }

    private boolean hasDuplicate(Map<MenuItem, Integer> input) {
        Set<MenuItem> menusWithoutDuplicates = new HashSet<>(input.keySet());
        return menusWithoutDuplicates.size() != input.size();
    }

    private boolean isOrderQuantityWithinLimit(Map<MenuItem, Integer> input) {
        return input.values().stream()
                .reduce(0, Integer::sum) <= MAXIMUM_PURCHASE_COUNT;
    }

    private boolean isOrderQuantityPositive(Map<MenuItem, Integer> input) {
        return input.values().stream()
                .allMatch(quantity -> quantity > 0);
    }

    private boolean isComposedOfBeverage(Map<MenuItem, Integer> input) {
        return input.keySet().stream()
                .map(MenuCategory::findCategoryByMenuItem)
                .allMatch(category -> category.equals(MenuCategory.BEVERAGE));
    }

    public Map<MenuItem, Integer> getMenus() {
        return menus;
    }
}
