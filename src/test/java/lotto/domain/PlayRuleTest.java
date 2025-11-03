package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import lotto.LottoConfig;
import lotto.domain.repository.LottoRepository;
import lotto.util.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayRuleTest {
    PlayRule playRule;
    LottoRepository lottoRepository = new LottoRepository();
    PickRule<List<Integer>> pickRule;

    @BeforeEach
    void setUp() {
        lottoRepository.clear();
        playRule = LottoConfig.getDefaultLottoRule();
        pickRule = playRule.getPickRule();
    }

    @Test
    @DisplayName("2000원을 넣으면 기회를 2번 줌")
    void getPickableChance() {
        assertThat(playRule.getPickableChance(2000))
                .isEqualTo(2);
    }

    @Test
    @DisplayName("가격이 1000원 단위가 아닐 경우")
    void getPickableChanceCheck() {
        assertThatThrownBy(() -> playRule.getPickableChance(2002))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_INPUT_MONEY.getMessage());
    }

    @Test
    @DisplayName("가격에 음수를 넣으면 에러")
    void getPickableChanceMinus() {
        assertThatThrownBy(() -> playRule.getPickableChance(-2000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_INPUT_MONEY.getMessage());
    }

    @Test
    @DisplayName("4,000원 넣고 20,000원 벌었으면 수익률이 500%임")
    void getYield() {
        playRule.matchWinningRule(3, false);
        playRule.matchWinningRule(3, false);
        playRule.matchWinningRule(3, false);
        playRule.matchWinningRule(3, false);

        for (int i = 0; i < 4; i++) {
            Lotto lotto = Lotto.pickRuleFrom(pickRule);
            lottoRepository.save(lotto);
        }

        List<Lotto> lottoList = lottoRepository.findAll();
        assertThat(playRule.getYield(lottoList))
                .isEqualTo(500);
    }

    @Test
    @DisplayName("7,000원 넣고 20,000원 벌었으면 수익률이 285.7142..%임")
    void getYieldTest2() {
        playRule.matchWinningRule(3, false);
        playRule.matchWinningRule(3, false);
        playRule.matchWinningRule(3, false);
        playRule.matchWinningRule(3, false);

        for (int i = 0; i < 7; i++) {
            Lotto lotto = Lotto.pickRuleFrom(pickRule);
            lottoRepository.save(lotto);
        }

        List<Lotto> lottoList = lottoRepository.findAll();
        assertThat(playRule.getYield(lottoList))
                .isEqualTo(285.7);
    }

    @Test
    @DisplayName("3개 맞추고 보너스 맞춤, 그러나 3개 맞추고 보너스를 틀린 조건의 상금을 받음")
    void matchWinningRuleV2_true_but_false_matching() {
        playRule.matchWinningRule(3, true);

        Lotto lotto = Lotto.pickRuleFrom(pickRule);
        lottoRepository.save(lotto);

        List<Lotto> lottoList = lottoRepository.findAll();
        assertThat(playRule.getYield(lottoList))
                .isEqualTo(500);
    }

    @Test
    @DisplayName("같은 5점 중 보너스를 맞춤")
    void matchWinningRuleV2_same_score_but_true() {
        playRule.matchWinningRule(5, true);

        Lotto lotto = Lotto.pickRuleFrom(pickRule);
        lottoRepository.save(lotto);

        List<Lotto> lottoList = lottoRepository.findAll();
        assertThat(playRule.getYield(lottoList))
                .isEqualTo(3000000);
    }

    @Test
    @DisplayName("3개 맞추고 보너스 맞춤, 그러나 3개 맞추고 보너스를 틀린 조건의 상금을 받음")
    void matchWinningRuleV2_same_score_but_false() {
        playRule.matchWinningRule(5, false);

        Lotto lotto = Lotto.pickRuleFrom(pickRule);
        lottoRepository.save(lotto);

        List<Lotto> lottoList = lottoRepository.findAll();
        assertThat(playRule.getYield(lottoList))
                .isEqualTo(150000);
    }

}