package christmas.menu.domain.repository;

import christmas.menu.domain.Appetizer;
import christmas.menu.domain.Beverage;
import christmas.menu.domain.Desert;
import christmas.menu.domain.Main;
import christmas.menu.domain.MenuItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MenuRepository {

    private final List<MenuItem> items = new ArrayList<>();

    public MenuRepository() {
        addItemsByCategory(Appetizer.class);
        addItemsByCategory(Main.class);
        addItemsByCategory(Desert.class);
        addItemsByCategory(Beverage.class);
    }

    public List<MenuItem> findItemsByCategory(Class<? extends Enum<?>> category) {
        return items.stream()
                .filter(item -> item.getClass().equals(category))
                .toList();
    }

    public Optional<MenuItem> findItemByName(String name) {
        return items.stream().filter(item -> item.getName().equals(name)).findFirst();
    }

    private <E extends Enum<E> & MenuItem> void addItemsByCategory(Class<E> enumClass) {
        items.addAll(
                Arrays.stream(enumClass.getEnumConstants())
                        .toList()
        );
    }
}
