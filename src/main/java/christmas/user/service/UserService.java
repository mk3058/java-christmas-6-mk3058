package christmas.user.service;

import christmas.menu.domain.MenuCategory;
import christmas.menu.domain.MenuItem;
import christmas.message.Error;
import christmas.user.domain.Order;
import christmas.user.domain.VisitDate;
import java.util.HashMap;
import java.util.Map;

public class UserService {

    public Order newOrder(Map<String, Integer> input) {
        Map<MenuItem, Integer> order = new HashMap<>();

        try {
            input.forEach((key, value) -> order.put(MenuCategory.findMenuItemByName(key), value));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(Error.INVALUD_ORDER.toString());
        }
        return new Order(order);
    }

    public VisitDate newVisitDate(int date) {
        return new VisitDate(date);
    }
}
