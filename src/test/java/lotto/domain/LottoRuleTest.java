package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import lotto.LottoConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoRuleTest {
    LottoRule lottoRule;

    @BeforeEach
    void setUp() {
        lottoRule = LottoConfig.getDefaultLottoRule();
    }

    @Test
    @DisplayName("2000원을 넣으면 기회를 2번 줌")
    void getPickableChance() {
        assertThat(lottoRule.getPickableChance(2000))
                .isEqualTo(2);
    }

    @Test
    @DisplayName("4,000원 넣고 20,000원 벌었으면 수익률이 500%임")
    void getYield() {
        lottoRule.matchWinningRule(3, false);
        lottoRule.matchWinningRule(3, false);
        lottoRule.matchWinningRule(3, false);
        lottoRule.matchWinningRule(3, false);

        assertThat(lottoRule.getYield(4))
                .isEqualTo(500);
    }

    @Test
    @DisplayName("7,000원 넣고 20,000원 벌었으면 수익률이 285.7142..%임")
    void getYieldTest2() {
        lottoRule.matchWinningRule(3, false);
        lottoRule.matchWinningRule(3, false);
        lottoRule.matchWinningRule(3, false);
        lottoRule.matchWinningRule(3, false);

        assertThat(lottoRule.getYield(7))
                .isEqualTo(285.7);
    }

    @Test
    @DisplayName("3개 맞추고 보너스 맞춤, 그러나 3개 맞추고 보너스를 틀린 조건의 상금을 받음")
    void matchWinningRuleV2_true_but_false_matching() {
        lottoRule.matchWinningRule(3, true);

        assertThat(lottoRule.getYield(1))
                .isEqualTo(500);
    }

    @Test
    @DisplayName("같은 5점 중 보너스를 맞춤")
    void matchWinningRuleV2_same_score_but_true() {
        lottoRule.matchWinningRule(5, true);

        assertThat(lottoRule.getYield(1))
                .isEqualTo(3000000);
    }

    @Test
    @DisplayName("3개 맞추고 보너스 맞춤, 그러나 3개 맞추고 보너스를 틀린 조건의 상금을 받음")
    void matchWinningRuleV2_same_score_but_false() {
        lottoRule.matchWinningRule(5, false);

        assertThat(lottoRule.getYield(1))
                .isEqualTo(150000);
    }

}