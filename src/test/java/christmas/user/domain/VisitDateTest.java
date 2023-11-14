package christmas.user.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateTest {

    @ParameterizedTest
    @DisplayName("getDayOfWeek: 적절한 요일을 반환한다")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7})
    void getDayOfWeek(int day) {

        LocalDate date = LocalDate.of(2023, 12, day);

        assertThat(new VisitDate(day).getDayOfWeek()).isEqualTo(date.getDayOfWeek());
    }

    @ParameterizedTest
    @DisplayName("isWeekend: 요구사항에 따라 금, 토요일일 경우 true를 반환한다")
    @ValueSource(ints = {1, 2})
    void isWeekend_true(int day) {
        assertThat(new VisitDate(day).isWeekend()).isTrue();
    }

    @ParameterizedTest
    @DisplayName("isWeekend: 요구사항에 따라 금, 토요일이 아닐 경우 false를 반환한다")
    @ValueSource(ints = {3, 4, 5, 6, 7})
    void isWeekend_false(int day) {
        assertThat(new VisitDate(day).isWeekend()).isFalse();
    }
}