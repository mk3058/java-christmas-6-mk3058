package christmas.menu.service;

import christmas.menu.domain.MenuCategory;
import christmas.menu.presentation.dto.MenuResponse;
import java.util.List;

public class MenuService {

    public List<MenuResponse> findItemsByCategory(MenuCategory category) {
        return category.menus().stream()
                .map(MenuResponse::fromMenuItem)
                .toList();
    }

    public List<MenuResponse> findAllItems() {
        return MenuCategory.allMenus().stream()
                .map(MenuResponse::fromMenuItem)
                .toList();
    }

    public MenuResponse findItemByName(String name) {
        return MenuCategory.findMenuItemByName(name)
                .map(MenuResponse::fromMenuItem)
                .orElseThrow(IllegalArgumentException::new);
    }
}
