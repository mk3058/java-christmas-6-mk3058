package christmas.menu.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

    public static Optional<MenuItem> findMenuItemByName(String name) {
        return MenuCategory.allMenus().stream()
                .filter(menuItem -> menuItem.getName().equals(name))
                .findFirst();
    }

    public static Optional<MenuCategory> findCategoryByMenuItem(MenuItem menuItem) {
        return Arrays.stream(MenuCategory.values())
                .filter(menuCategory -> menuCategory.menus().contains(menuItem))
                .findFirst();
    }
}