package christmas.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.Console;
import christmas.message.Error;
import christmas.user.domain.Order;
import christmas.view.InputView;
import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class UserServiceTest {

    private final InputView inputView = new InputView();
    private final UserService userService = new UserService();

    @BeforeEach
    void setUp() {
        Console.close();
    }

    @AfterAll
    static void cleanup() {
        Console.close();
    }

    @ParameterizedTest
    @DisplayName("newOrder: 정상 주문이 주어졌을 때 Order 객체가 생성된다")
    @MethodSource("provideValidInput")
    void newOrder(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        Map<String, Integer> expected = inputView.getMenus();
        Map<String, Integer> actual = new LinkedHashMap<>();

        Order order = userService.newOrder(expected);
        order.getMenus().forEach((key, value) -> actual.put(key.getName(), value));
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("newOrder: 잘못된 주문이 주어졌을 때 예외가 발생한다")
    @MethodSource("provideInvalidInput")
    void newOrder_invalid_input(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        assertThatThrownBy(() -> {
            Map<String, Integer> inputMap = inputView.getMenus();
            userService.newOrder(inputMap);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Error.INVALUD_ORDER.toString());
    }

    @ParameterizedTest
    @DisplayName("newVisitDate: 정상 날짜가 입력되었을 때 VisitDate 객체 생성에 성공한다")
    @ValueSource(ints = {1, 15, 20, 31})
    void newVisitDate(int value) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(String.valueOf(value).getBytes());
        System.setIn(inputStream);

        int date = inputView.getDate();
        assertThat(userService.newVisitDate(date).getDate()).isEqualTo(LocalDate.of(2023, 12, value));
    }

    @ParameterizedTest
    @DisplayName("newVisitDate: 잘못된 날짜가 입력되었을 때 예외가 발생한다")
    @ValueSource(ints = {-1, 0, 32, 50})
    void newVisitDate_invlaid_input(int value) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(String.valueOf(value).getBytes());
        System.setIn(inputStream);

        assertThatThrownBy(() -> {
            int date = inputView.getDate();
            userService.newVisitDate(date);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Error.INVALUD_DATE.toString());

    }

    private static Stream<String> provideValidInput() {
        List<String> validInput = new ArrayList<>();

        validInput.addAll(Arrays.asList(
                "해산물파스타-2,레드와인-1,초코케이크-1",
                "해산물파스타-2, 레드와인-1, 초코케이크-1",
                "해산물파스타 - 2 , 레드와인 - 1 , 초코케이크 - 1"
        ));
        return validInput.stream();
    }

    private static Stream<String> provideInvalidInput() {
        List<String> invalidInput = new ArrayList<>();

        invalidInput.addAll(Arrays.asList(
                "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-0", //invalid purchase quantity
                "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-17", //quantity over 20
                "제로콜라-1,샴페인-10", //order composed of beverages
                "없는메뉴-10, 제로콜라-5", //unvalid menu name
                "티본스테이크-1,,바비큐립-1,초코케이크-2,제로콜라-1", //invalid format
                "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1,제로콜라-2" //duplicate
        ));
        return invalidInput.stream();
    }
}