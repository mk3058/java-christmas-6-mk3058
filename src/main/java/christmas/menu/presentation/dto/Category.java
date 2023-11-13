package christmas.menu.presentation.dto;

import christmas.menu.domain.MenuItem;
import java.util.Arrays;

public enum Category {
    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESERT("디저트"),
    BEVERAGE("음료");

    private final String category;

    Category(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return '<' + category + '>';
    }

    public static Category getCategoryFromMenuItem(MenuItem category) {
        return Arrays.stream(values())
                .filter(value -> isMatch(value, category))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    private static boolean isMatch(Category category, MenuItem menuItem) {
        return category.name().equalsIgnoreCase(menuItem.getClass().getSimpleName());
    }
}
