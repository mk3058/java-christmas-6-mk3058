package christmas.menu.domain.repository;

import christmas.menu.domain.Menu;
import christmas.menu.domain.MenuItem;
import java.util.List;
import java.util.Optional;

public class MenuRepository {

    private final Menu menu = new Menu();

    public List<MenuItem> findItemsByCategory(Class<? extends Enum<?>> category) {
        return items.stream()
                .filter(item -> item.getClass().equals(category))
                .toList();
    }

    public Optional<MenuItem> findItemByName(String name) {
        return items.stream().filter(item -> item.getName().equals(name)).findFirst();
    }
}
