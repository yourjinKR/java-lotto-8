package lotto.domain;

import java.util.List;
import lotto.util.ErrorMessage;

public class LottoRule {
    private final int price;
    private final PickRule<List<Integer>> pickRule;
    private final List<Winning> winningRule;

    public LottoRule(int price, PickRule<List<Integer>> pickRule, List<Winning> winningRule) {
        this.price = price;
        this.pickRule = pickRule;
        this.winningRule = winningRule;
    }

    public PickRule<List<Integer>> getPickRule() {
        return pickRule;
    }

    // 금액만큼 횟수 제공
    public int getChancePickable(int purchaseAmount) {
        if (purchaseAmount % this.price != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_MONEY.getMessage());
        }
        return purchaseAmount / this.price;
    }
}
