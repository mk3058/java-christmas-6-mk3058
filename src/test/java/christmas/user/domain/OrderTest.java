package christmas.user.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.menu.domain.Main;
import christmas.menu.domain.MenuItem;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    @DisplayName("getPrice: 적절한 총 금액을 반환한다")
    void getPrice() {
        BigDecimal expectedPrice = Arrays.stream(Main.values())
                .map(Main::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Map<MenuItem, Integer> orders = new HashMap<>();
        Arrays.stream(Main.values()).forEach(menu -> orders.put(menu, 1));
        Order order = new Order(orders);

        assertThat(order.getPrice()).isEqualTo(expectedPrice);
    }
}