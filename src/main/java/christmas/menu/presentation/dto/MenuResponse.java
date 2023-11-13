package christmas.menu.presentation.dto;

import christmas.menu.domain.MenuItem;
import java.math.BigDecimal;

public record MenuResponse(Category category, String name, BigDecimal price) {

    public static MenuResponse fromMenuItem(MenuItem item) {
        return new MenuResponse(Category.getCategoryFromMenuItem(item), item.getName(), item.getPrice());
    }
}
