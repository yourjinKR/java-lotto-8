package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningTest {
    Winning winning;

    @BeforeEach
    void setUp() {
        winning = new Winning(3, false, 5000);
    }

    @Test
    @DisplayName("매칭되는 조건이면 true")
    void isMatchedTrue() {
        assertThat(winning.isMatched(3, false))
                .isTrue();
    }

    @Test
    @DisplayName("매칭되는 조건이면 false")
    void isMatchedFalse() {
        assertThat(winning.isMatched(4, false))
                .isFalse();
    }

    @Test
    @DisplayName("매칭된거라면 카운트가 증가")
    void countUpMatchedAmountTest() {
        winning.countUpIfMatched(3, false);
        List<Winning> list = List.of(winning);

        assertThat(list)
                .extracting("matchedCount")
                .containsOnly(1);
    }

    @Test
    @DisplayName("많이 매칭됐다면 계속 증가")
    void countUpManyMatchedAmountTest() {
        winning.countUpIfMatched(3, false);
        winning.countUpIfMatched(3, false);
        winning.countUpIfMatched(3, false);
        winning.countUpIfMatched(3, false);
        List<Winning> list = List.of(winning);

        assertThat(list)
                .extracting("matchedCount")
                .containsOnly(4);
    }

    @Test
    @DisplayName("매칭된게 아니라면 카운트가 증가하지 않음")
    void countUpMatchedAmountFailTest() {
        winning.countUpIfMatched(6, true);
        List<Winning> list = List.of(winning);

        assertThat(list)
                .extracting("matchedCount")
                .containsOnly(0);
    }

    @Test
    @DisplayName("5000원짜리 2번 당첨됐으니 상금은 만원")
    void getTotalPrizeMoneyTest() {
        winning.countUpIfMatched(3, false);
        winning.countUpIfMatched(3, false);
        assertThat(winning.getTotalPrizeMoney())
                .isEqualTo(10_000);
    }
}