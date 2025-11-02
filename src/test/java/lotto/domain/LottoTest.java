package lotto.domain;

import lotto.util.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoTest {
    Lotto lotto;
    WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 1);
    }

    @Test
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다")
    void invalidLottoNumberAmount() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.UNMATCH_WINNING_AMOUNT.getMessage());
    }

    @Test
    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    void duplicateLottoNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATE_LOTTO_NUMBER.getMessage());
    }

    @Test
    @DisplayName("로또 번호에 범위 밖 숫자가 있다면 예외가 발생한다")
    void outOfRangeLottoNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 11111)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.OUT_OF_RANGE_NUMBER.getMessage());
    }

    @Test
    @DisplayName("보너스 번호가 있으면 true 반환")
    void checkBonusNumberTrueTest() {
        assertThat(lotto.isBonusMatched(winningLotto))
                .isTrue();
    }

    @Test
    @DisplayName("보너스 번호가 있으면 false 반환")
    void checkBonusNumberFalseTest() {
        assertThat(lotto.isBonusMatched(new WinningLotto(List.of(1,2,3,4,5,6), 45)))
                .isFalse();
    }

    @Test
    @DisplayName("다일치하면 6점")
    void getAllMatchingScoreTest() {
        assertThat(lotto.getMatchingScore(winningLotto))
                .isEqualTo(6);
    }

    @Test
    @DisplayName("일치하는 번호만큼 점수를 계산함")
    void getMatchingScoreTest() {
        assertThat(lotto.getMatchingScore(new WinningLotto(List.of(1, 2, 3, 4, 7, 8), 1)))
                .isEqualTo(4);
    }
}
