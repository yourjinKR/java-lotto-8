package lotto.repository;


import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.PickRule;

public class LottoRepository {

    public List<Lotto> registerAsAmountByRule(int amount, PickRule<List<Integer>> pickRule) {
        List<Lotto> lottoList = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            List<Integer> numbers = pickRule.pick();
            Lotto lotto = new Lotto(numbers);
            lottoList.add(lotto);
        }

        return lottoList;
    }
}
