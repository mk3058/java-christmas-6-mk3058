package christmas.menu.presentation.dto;

import christmas.menu.domain.MenuCategory;
import christmas.menu.domain.MenuItem;
import java.math.BigDecimal;

public record MenuResponse(MenuCategory category, String name, BigDecimal price) {

    public static MenuResponse fromMenuItem(MenuItem item) {
        return new MenuResponse(MenuCategory.findCategoryByMenuItem(item), item.getName(), item.getPrice());
    }
}
