package christmas.event.presentation.dto;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public record EventResult(String eventName, BigDecimal benefitAmount) {

    @Override
    public String toString() {
        return String.format("%s: -%s원", eventName, NumberFormat.getInstance(Locale.KOREA).format(benefitAmount));
    }
}
