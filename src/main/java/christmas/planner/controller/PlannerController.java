package christmas.planner.controller;

import christmas.user.domain.Order;
import christmas.user.domain.VisitDate;
import christmas.user.service.UserService;
import christmas.view.InputView;
import java.util.Map;

public class PlannerController {

    private final InputView inputView = new InputView();
    private final UserService userService = new UserService();

    public void start() {
        VisitDate date = getDate();
        Order order = getOrder();
        //TODO 이후 기능 구현
    }

    private VisitDate getDate() {
        while (true) {
            try {
                return userService.newVisitDate(inputView.getDate());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Order getOrder() {
        while (true) {
            try {
                return userService.newOrder(getMenus());
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
