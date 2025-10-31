package lotto.repository;


import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.PickRule;

public class LottoRepository {
    private final List<Lotto> lottoList = new ArrayList<>();

    public List<Lotto> createAsAmountByRule(int amount, PickRule<List<Integer>> pickRule) {
        List<Lotto> lottoList = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            List<Integer> numbers = pickRule.pick();
            Lotto lotto = new Lotto(numbers);
            lottoList.add(lotto);
        }

        this.lottoList.addAll(lottoList);

        return lottoList;
    }
}
