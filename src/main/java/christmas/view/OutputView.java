package christmas.view;

import christmas.event.domain.Badge;
import christmas.event.domain.DiscountEvent;
import christmas.event.domain.GiftEvent;
import christmas.event.presentation.dto.EventResult;
import christmas.message.Output;
import christmas.user.domain.Order;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NOTHING = "없음";

    public void printOrder(Order order) {
        String menu = order.toString();
        System.out.println(Output.ORDERED_MENU.toString(menu));
    }

    public void printTotalPrice(Order order) {
        String price = formattingPrice(order.getPrice());
        System.out.println(Output.TOTAL_COST.toString(price));
    }

    public void printEventResult(List<EventResult> results, Order order) {
        printGiftMenu(results);
        printBenefitDetails(results);
        printTotalBenefitAmount(results);
        printFinalPrice(results, order);
        printEventBadge(results);
    }

    private void printGiftMenu(List<EventResult> results) {
        Optional<GiftEvent> giftEvent = results.stream()
                .map(EventResult::event)
                .filter(event -> event instanceof GiftEvent)
                .findFirst()
                .map(event -> (GiftEvent) event);
        String giftDetails = giftEvent
                .map(gift -> String.join(" ", gift.getGiftName(), gift.getGiftAmount() + "개"))
                .orElse(NOTHING);

        System.out.println(Output.GIFT_PROMOTION.toString(giftDetails));
    }

    private void printBenefitDetails(List<EventResult> results) {
        String details = results.stream()
                .map(EventResult::toString)
                .collect(Collectors.joining("\n"));

        if (details.isEmpty()) {
            details = NOTHING;
        }
        System.out.println(Output.PROMOTION_LIST.toString(details));
    }

    private void printTotalBenefitAmount(List<EventResult> results) {
        BigDecimal totalBenefitAmount = getTotalBenefitAmount(results).multiply(BigDecimal.valueOf(-1));

        System.out.println(Output.PROMOTION_PRICE.toString(formattingPrice(totalBenefitAmount)));
    }

    private void printFinalPrice(List<EventResult> results, Order order) {
        BigDecimal finalPrice = order.getPrice().subtract(getTotalDiscountAmount(results));
        System.out.println(Output.COST_AFTER_PROMOTION.toString(formattingPrice(finalPrice)));
    }

    private void printEventBadge(List<EventResult> results) {
        System.out.println(Output.BADGE.toString(Badge.fromBenefitAmount(getTotalBenefitAmount(results))));
    }

    private BigDecimal getTotalBenefitAmount(List<EventResult> results) {
        return results.stream()
                .map(EventResult::benefitAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getTotalDiscountAmount(List<EventResult> results) {
        return results.stream()
                .filter(result -> result.event() instanceof DiscountEvent)
                .map(EventResult::benefitAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private String formattingPrice(BigDecimal price) {
        String formattedPrice = NumberFormat.getInstance(Locale.KOREA).format(price);
        return formattedPrice + "원";
    }
}
