package lotto;

import lotto.domain.Lotto;
import lotto.util.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
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
                .hasMessage(ErrorMessage.INVALID_RANGE_NUMBER.getMessage());
    }

}
