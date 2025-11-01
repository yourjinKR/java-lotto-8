package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningTest {

    @Test
    @DisplayName("매칭되는 조건이면 true")
    void isMatchedTrue() {
        Winning winning = new Winning(3, false, 5000);
        assertThat(winning.isMatched(3, false))
                .isTrue();
    }

    @Test
    @DisplayName("매칭되는 조건이면 false")
    void isMatchedFalse() {
        Winning winning = new Winning(3, false, 5000);
        assertThat(winning.isMatched(4, false))
                .isFalse();
    }
}