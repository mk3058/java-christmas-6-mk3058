package christmas.menu.service;

import christmas.menu.domain.MenuItem;
import christmas.menu.domain.repository.MenuRepository;
import christmas.menu.presentation.dto.MenuResponse;
import java.util.List;

public class MenuService {

    private final MenuRepository menuRepository = new MenuRepository();

    public List<MenuResponse> findItemsByCategory(Enum<?> category) {
        List<MenuItem> items = menuRepository.findItemsByCategory(category.getDeclaringClass());
        return items.stream().map(MenuResponse::fromMenuItem).toList();
    }

    public MenuResponse findItemByName(String name) {
        return menuRepository.findItemByName(name).map(MenuResponse::fromMenuItem)
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
