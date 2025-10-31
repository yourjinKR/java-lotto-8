package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import lotto.LottoConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoRuleTest {

    @Test
    @DisplayName("2000원을 넣으면 기회를 2번 줌")
    void getPickableChance() {
        LottoRule lottoRule = LottoConfig.getDefaultLottoRule();
        assertThat(lottoRule.getPickableChance(2000))
                .isEqualTo(2);
    }
}