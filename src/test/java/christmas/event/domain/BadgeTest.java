package christmas.event.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BadgeTest {

    @ParameterizedTest
    @MethodSource("provideAmountsAndBadges")
    @DisplayName("fromBenefitAmount: 금액대 별 적절한 배지 반환에 성공한다")
    void fromBenefitAmount(BigDecimal amount, String expectedBadge) {
        String actualBadge = Badge.fromBenefitAmount(amount);

        assertThat(expectedBadge).isEqualTo(actualBadge);
    }

    private static Stream<Arguments> provideAmountsAndBadges() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(20000), "산타"),
                Arguments.of(BigDecimal.valueOf(10000), "트리"),
                Arguments.of(BigDecimal.valueOf(5000), "별"),
                Arguments.of(BigDecimal.valueOf(0), "없음"),
                Arguments.of(BigDecimal.valueOf(15000), "트리"),
                Arguments.of(BigDecimal.valueOf(2500), "없음"),
                Arguments.of(BigDecimal.valueOf(30000), "산타")
        );
    }
}