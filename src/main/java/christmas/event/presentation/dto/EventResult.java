package christmas.event.presentation.dto;

import christmas.event.domain.Event;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public record EventResult(Event event, BigDecimal benefitAmount) {

    @Override
    public String toString() {
        return String.format("%s: -%s원", event.getEventName(),
                NumberFormat.getInstance(Locale.KOREA).format(benefitAmount));
    }
}
