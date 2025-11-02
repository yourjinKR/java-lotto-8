package lotto.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoPickRule;
import lotto.domain.PickRule;
import lotto.domain.repository.LottoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoRepositoryTest {
    PickRule<List<Integer>> pickRule;
    LottoRepository lottoRepository;
    List<Lotto> lottoList;

    @BeforeEach
    void setUp() {
        pickRule = new LottoPickRule(1, 45, 6);
        lottoRepository = new LottoRepository();

        lottoRepository.creatByPickRule(pickRule);
        lottoRepository.creatByPickRule(pickRule);
        lottoRepository.creatByPickRule(pickRule);
        lottoRepository.creatByPickRule(pickRule);
        lottoRepository.creatByPickRule(pickRule);

        lottoList = lottoRepository.findAll();
    }

    @Test
    @DisplayName("규칙에 따라 여러 로또들을 뽑음")
    void createAsAmountByRule() {
        assertThat(lottoList.size())
                .isEqualTo(5);
    }

    @Test
    @DisplayName("생성했던 로또들을 다시 불러옴")
    void findByAllTest() {
        assertThatList(lottoRepository.findAll())
                .isInstanceOf(List.class);
    }

    @Test
    @DisplayName("동일한 객체인지 확인")
    void findByAllEqualTo() {
        Lotto old1 = lottoList.getFirst();
        Lotto new1 = lottoList.getFirst();

        assertThat(old1 == new1)
                .isTrue();
    }
}