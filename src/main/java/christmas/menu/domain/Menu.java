package christmas.menu.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Menu {

    private final List<MenuItem> items;

    public Menu() {
        List<MenuItem> temp = new ArrayList<>();
        addItemsByCategory(Appetizer.class, temp);
        addItemsByCategory(Main.class, temp);
        addItemsByCategory(Desert.class, temp);
        addItemsByCategory(Beverage.class, temp);

        items = Collections.unmodifiableList(temp);
    }

    private <E extends Enum<E> & MenuItem> void addItemsByCategory(Class<E> enumClass, List<MenuItem> menu) {
        menu.addAll(
                Arrays.stream(enumClass.getEnumConstants())
                        .toList()
        );
    }
}
