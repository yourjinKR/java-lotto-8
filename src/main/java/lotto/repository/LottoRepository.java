package lotto.repository;


import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.PickRule;

public class LottoRepository {
    private static final List<Lotto> lottoList = new ArrayList<>();

    public void creatByPickRule(PickRule<List<Integer>> pickRule) {
        List<Integer> numbers = pickRule.pick();
        Lotto lotto = new Lotto(numbers);

        lottoList.add(lotto);
    }

    public List<Lotto> findAll() {
        return new ArrayList<>(lottoList);
    }

    public void clear() {
        lottoList.clear();
    }
}
