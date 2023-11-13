package christmas;

import christmas.planner.controller.PlannerController;

public class Application {
    public static void main(String[] args) {
        PlannerController plannerController = new PlannerController();

        plannerController.start();
    }
}
