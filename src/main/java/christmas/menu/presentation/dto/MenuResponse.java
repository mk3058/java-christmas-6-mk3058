package christmas.menu.presentation.dto;

import christmas.menu.domain.MenuCategory;
import christmas.menu.domain.MenuItem;
import java.math.BigDecimal;

public record MenuResponse(MenuCategory category, String name, BigDecimal price) {

    public static MenuResponse fromMenuItem(MenuItem item) {
        MenuCategory category = MenuCategory.findCategoryByMenuItem(item).orElseThrow(IllegalArgumentException::new);

        return new MenuResponse(category, item.getName(), item.getPrice());
    }
}
