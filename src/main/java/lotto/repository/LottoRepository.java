package lotto.repository;


import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.PickRule;
import lotto.domain.Winning;

public class LottoRepository {

    public List<Winning> getDefaultWinningRule() {
        return List.of(
                new Winning(3, false, 5_000),
                new Winning(4, false, 50_000),
                new Winning(5, false, 1_500_000),
                new Winning(5, true, 30_000_000),
                new Winning(6, true, 2_000_000_000)
        );
    }

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
