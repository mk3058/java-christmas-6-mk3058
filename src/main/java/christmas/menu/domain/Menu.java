package christmas.menu.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Menu {

    private final List<MenuItem> items = new ArrayList<>();

    public Menu() {
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
