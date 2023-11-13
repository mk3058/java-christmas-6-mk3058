package christmas.user.service;

import christmas.menu.domain.MenuCategory;
import christmas.menu.domain.MenuItem;
import christmas.user.domain.Order;
import java.util.HashMap;
import java.util.Map;

public class OrderService {

    public Order newOrder(Map<String, Integer> input) {
        Map<MenuItem, Integer> order = new HashMap<>();

        input.forEach((key, value) -> order.put(MenuCategory.findMenuItemByName(key), value));
        return new Order(order);
    }
}
