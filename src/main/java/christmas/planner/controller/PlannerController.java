package christmas.planner.controller;

import christmas.user.domain.Order;
import christmas.user.service.OrderService;
import christmas.view.InputView;
import java.util.Map;

public class PlannerController {

    private final InputView inputView = new InputView();
    private final OrderService orderService = new OrderService();

    public void start() {
        int date = getDate();
        Order order = getOrder();

        System.out.println(date);
        System.out.println(order.getMenus().toString());
    }

    private int getDate() {
        while (true) {
            try {
                return inputView.getDate();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Order getOrder() {
        while (true) {
            try {
                return orderService.newOrder(getMenus());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Map<String, Integer> getMenus() {
        while (true) {
            try {
                return inputView.getMenus();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
