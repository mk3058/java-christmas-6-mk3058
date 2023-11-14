package christmas.event.domain;

import java.math.BigDecimal;
import java.util.Arrays;

public enum Badge {

    SANTA("산타", BigDecimal.valueOf(20_000)),
    TREE("트리", BigDecimal.valueOf(10_000)),
    STAR("별", BigDecimal.valueOf(5_000)),
    NOTHING("없음", BigDecimal.valueOf(0));

    private final String badge;
    private final BigDecimal threshold;

    Badge(String badge, BigDecimal threshold) {
        this.badge = badge;
        this.threshold = threshold;
    }

    public static String fromBenefitAmount(BigDecimal amount) {
        Badge userBadge = Arrays.stream(Badge.values())
                .filter(badge -> amount.compareTo(badge.threshold) >= 0)
                .findFirst()
                .orElse(Badge.NOTHING);
        return userBadge.badge;
    }
}
