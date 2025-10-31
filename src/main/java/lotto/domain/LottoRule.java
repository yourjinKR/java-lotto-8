package lotto.domain;

import java.util.List;

public class LottoRule {
    private final int price;
    private final PickRule<List<Integer>> pickRule;
    private final List<Winning> winningRule;

    public LottoRule(int price, PickRule<List<Integer>> pickRule, List<Winning> winningRule) {
        this.price = price;
        this.pickRule = pickRule;
        this.winningRule = winningRule;
    }
}
