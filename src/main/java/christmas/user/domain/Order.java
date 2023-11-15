package christmas.user.domain;

import christmas.menu.domain.MenuCategory;
import christmas.menu.domain.MenuItem;
import christmas.message.Error;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {

    private static final Integer MAXIMUM_PURCHASE_COUNT = 20;

    private final Map<MenuItem, Integer> menus;

    public Order(Map<MenuItem, Integer> menus) {
        validate(menus);
        this.menus = Collections.unmodifiableMap(menus);
    }

    public BigDecimal getPrice() {
        return menus.entrySet().stream()
                .map(entry -> {
                    MenuItem item = entry.getKey();
                    int quantity = entry.getValue();

                    return item.getPrice().multiply(BigDecimal.valueOf(quantity));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void validate(Map<MenuItem, Integer> input) {
        if (!isOrderQuantityPositive(input) || !isOrderQuantityWithinLimit(input) || isComposedOfBeverage(input)) {
            throw new IllegalArgumentException(Error.INVALUD_ORDER.toString());
        }
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

    @Override
    public String toString() {
        return menus.entrySet().stream()
                .map(entry -> entry.getKey().toString() + " " + entry.getValue() + "개\n")
                .collect(Collectors.joining());
    }

    public Map<MenuItem, Integer> getMenus() {
        return new HashMap<>(menus);
    }
}
