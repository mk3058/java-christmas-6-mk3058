package christmas.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StringUtilTest {

    @ParameterizedTest
    @DisplayName("split: 공백을 무시하고 split에 성공한다")
    @ValueSource(strings = {"가, 나, 다", "가  ,  나,  다"})
    void split(String value) {
        String delimiter = ",";
        List<String> expected = List.of("가,나,다".split(delimiter));

        assertThat(StringUtil.split(value, delimiter)).isEqualTo(expected);
    }
}