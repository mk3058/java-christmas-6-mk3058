package christmas.planner.controller;

import christmas.event.presentation.dto.EventResult;
import christmas.event.service.EventService;
import christmas.message.Output;
import christmas.user.domain.Order;
import christmas.user.domain.VisitDate;
import christmas.user.service.UserService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Map;

public class PlannerController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final UserService userService = new UserService();
    private final EventService eventService = new EventService();

    public void start() {
        System.out.println(Output.WELCOME.toString());
        VisitDate visitDate = getDate();
        Order order = getOrder();

        List<EventResult> results = eventService.applyEvents(visitDate, order);
        System.out.println(Output.PROMOTION_PROMPT.toString(String.valueOf(visitDate.getDayOfMonth())));
        outputView.printOrder(order);
        outputView.printTotalPrice(order);
        outputView.printEventResult(results, order);
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
