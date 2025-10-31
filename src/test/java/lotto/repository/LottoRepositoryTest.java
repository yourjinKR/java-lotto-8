package lotto.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoPickRule;
import lotto.domain.PickRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoRepositoryTest {

    @Test
    @DisplayName("규칙에 따라 여러 로또들을 뽑음")
    void createAsAmountByRule() {
        PickRule<List<Integer>> pickRule = new LottoPickRule(1,45,6);
        LottoRepository lottoRepository = new LottoRepository();
        List<Lotto> lottoList = lottoRepository.createAsAmountByRule(5, pickRule);

        assertThat(lottoList.size())
                .isEqualTo(5);
    }
}