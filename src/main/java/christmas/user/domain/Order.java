package christmas.user.domain;

import christmas.menu.domain.MenuItem;
import java.util.List;

public class Order {

    private final List<MenuItem> menus;

    public Order(List<MenuItem> menus) {
        this.menus = menus;
    }
}
