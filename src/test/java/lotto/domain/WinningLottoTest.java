package lotto.domain;

import static org.assertj.core.api.Assertions.*;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningLottoTest {
    WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 6);
    }

    @Test
    @DisplayName("당첨 로또도 똑같이 숫자들을 가짐")
    void getNumbersTest() {
        assertThat(winningLotto.getNumbers())
                .isEqualTo(List.of(1,2,3,4,5,6));
    }

    @Test
    @DisplayName("추가로 보너스 번호를 가짐")
    void getBonusNumber() {
        assertThat(winningLotto.getBonusNumber())
                .isEqualTo(6);
    }

    @ParameterizedTest
    @MethodSource("generateData")
    @DisplayName("WinningLotto 또한 Lotto와 같은 검증을 거침")
    void validateLottoTest(List<Integer> numbers, int bonusNumber) {
        assertThatThrownBy(() -> new WinningLotto(numbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(Arrays.asList(1,2,3,4,5), 6),
                Arguments.of(Arrays.asList(1,2,3,4,5,5), 6),
                Arguments.of(Arrays.asList(1,2,3,4,5,100), 6)
        );
    }

    @Test
    @DisplayName("보너스 번호 검증")
    void validateBonusNumberTest() {
        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 46))
                .isInstanceOf(IllegalArgumentException.class);
    }
}