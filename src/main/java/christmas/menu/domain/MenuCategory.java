package christmas.menu.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public enum MenuCategory {

    APPETIZER(Appetizer.values()),
    MAIN(Main.values()),
    DESERT(Desert.values()),
    BEVERAGE(Beverage.values());

    private final List<MenuItem> menus;

    MenuCategory(MenuItem... menus) {
        this.menus = new ArrayList<MenuItem>(List.of(menus));
    }

    public List<MenuItem> menus() {
        return new ArrayList<>(menus);
    }

    public static List<MenuItem> allMenus() {
        return Arrays.stream(MenuCategory.values())
                .map(MenuCategory::menus)
                .flatMap(Collection::stream)
                .toList();
    }

    public static MenuItem findMenuItemByName(String name) {
        return MenuCategory.allMenus().stream()
                .filter(menuItem -> menuItem.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static MenuCategory findCategoryByMenuItem(MenuItem menuItem) {
        return Arrays.stream(MenuCategory.values())
                .filter(menuCategory -> menuCategory.menus().contains(menuItem))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}