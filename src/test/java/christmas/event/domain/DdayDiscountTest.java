package christmas.event.domain;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.Console;
import christmas.user.domain.Order;
import christmas.user.domain.VisitDate;
import christmas.user.service.UserService;
import christmas.view.InputView;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DdayDiscountTest {

    @AfterAll
    static void clean() {
        Console.close();
    }

    @ParameterizedTest
    @DisplayName("isEligible: 올바른 입력이 주어진 경우 true 를 반환한다")
    @MethodSource("provideValidInput")
    void isEligible(Order order, VisitDate visit) {
        DdayDiscount ddayDiscount = new DdayDiscount();

        assertThat(ddayDiscount.isEligible(visit, order)).isTrue();
    }

    @ParameterizedTest
    @DisplayName("isEligible: 잘못된 입력이 주어진 경우 false 를 반환한다")
    @MethodSource("provideInValidInput")
    void isEligible_false(Order order, VisitDate visit) {
        DdayDiscount ddayDiscount = new DdayDiscount();

        assertThat(ddayDiscount.isEligible(visit, order)).isFalse();
    }

    private static Stream<Arguments> provideValidInput() {
        List<String> inputs = Arrays.asList(
                "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1",
                "티본스테이크-1,바비큐립-1,초코케이크-2,샴페인-10"
        );
        List<Integer> dates = Arrays.asList(1, 10, 25);

        return parseMenu(inputs).stream()
                .flatMap(order -> parseDate(dates).stream().map(date -> Arguments.of(order, date)));
    }

    private static Stream<Arguments> provideInValidInput() {
        List<String> inputs = Arrays.asList(
                "티본스테이크-1",
                "티본스테이크-1,바비큐립-1"
        );
        List<Integer> dates = Arrays.asList(26, 31);

        return parseMenu(inputs).stream()
                .flatMap(order -> parseDate(dates).stream().map(date -> Arguments.of(order, date)));
    }

    private static List<Order> parseMenu(List<String> inputs) {
        UserService userService = new UserService();
        InputView inputView = new InputView();

        return inputs.stream()
                .map(input -> {
                    Console.close();
                    System.setIn(new ByteArrayInputStream(input.getBytes()));
                    return userService.newOrder(inputView.getMenus());
                }).toList();
    }

    private static List<VisitDate> parseDate(List<Integer> dates) {
        UserService userService = new UserService();

        return dates.stream()
                .map(userService::newVisitDate)
                .toList();
    }
}