package christmas.event.service;

import christmas.event.domain.DdayDiscount;
import christmas.event.domain.Event;
import christmas.event.domain.Gift;
import christmas.event.domain.SpecialDiscount;
import christmas.event.domain.WeekdayDiscount;
import christmas.event.domain.WeekendDiscount;
import christmas.event.presentation.dto.EventResult;
import christmas.user.domain.Order;
import christmas.user.domain.VisitDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventService {

    List<Event> events = new ArrayList<>();

    public EventService() {
        events.addAll(Arrays.asList(
                new DdayDiscount(),
                new SpecialDiscount(),
                new WeekdayDiscount(),
                new WeekendDiscount(),
                new Gift()
        ));
    }

    public List<EventResult> applyEvents(VisitDate visitDate, Order order) {
        List<Event> applicableEvents = findApplicableEvents(visitDate, order);

        return applicableEvents.stream()
                .map(event -> new EventResult(event.getEventName(), event.getBenefitAmount(visitDate, order)))
                .toList();
    }

    private List<Event> findApplicableEvents(VisitDate visitDate, Order order) {
        return events.stream()
                .filter(event -> event.isEligible(visitDate, order))
                .toList();
    }
}
