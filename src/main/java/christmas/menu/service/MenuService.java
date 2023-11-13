package christmas.menu.service;

import christmas.menu.domain.Menu;
import christmas.menu.domain.MenuItem;
import christmas.menu.presentation.dto.MenuResponse;
import java.util.List;

public class MenuService {

    private Menu menu = new Menu();

    public List<MenuResponse> findItemsByCategory(Enum<?> category) {
        List<MenuItem> items = menu.findItemsByCategory(category.getDeclaringClass());
        return items.stream().map(MenuResponse::fromMenuItem).toList();
    }

    public MenuResponse findItemByName(String name) {
        return menu.findItemByName(name).map(MenuResponse::fromMenuItem)
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
