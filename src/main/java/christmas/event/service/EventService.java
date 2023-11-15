package christmas.event.service;

import christmas.event.domain.ChampagneGift;
import christmas.event.domain.DdayDiscount;
import christmas.event.domain.Event;
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

    List<Event> discountEvents = new ArrayList<>();

    public EventService() {
        discountEvents.addAll(Arrays.asList(
                new DdayDiscount(),
                new SpecialDiscount(),
                new WeekdayDiscount(),
                new WeekendDiscount(),
                new ChampagneGift()
        ));
    }

    public List<EventResult> applyEvents(VisitDate visitDate, Order order) {
        List<Event> applicableDiscountEvents = findApplicableEvents(visitDate, order);

        return applicableDiscountEvents.stream()
                .map(event -> new EventResult(event, event.getBenefitAmount(visitDate, order)))
                .toList();
    }

    private List<Event> findApplicableEvents(VisitDate visitDate, Order order) {
        return discountEvents.stream()
                .filter(event -> event.isEligible(visitDate, order))
                .toList();
    }
}
