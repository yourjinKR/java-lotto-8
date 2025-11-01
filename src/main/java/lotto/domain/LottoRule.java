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

    public List<Winning> getWinningRule() {
        return winningRule;
    }

    // 금액만큼 횟수 제공
    public int getPickableChance(int purchaseAmount) {
        if (purchaseAmount % this.price != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_MONEY.getMessage());
        }
        return purchaseAmount / this.price;
    }

    public void matchWinningRule(int matchingScore, boolean isBonusMatched) {
        winningRule.forEach(winning -> winning.countUpIfMatched(matchingScore, isBonusMatched));
    }

    // false 허용하는 매칭
    public void matchWinningRuleV2(int matchingScore, boolean isBonusMatched) {
        List<Winning> matchWinnings = winningRule.stream()
                .filter(winning -> winning.getScore() == matchingScore)
                .toList();

        if (matchWinnings.size() == 1) {
            Winning matchWinning = matchWinnings.getFirst();
            matchWinning.countUpIfMatched(matchingScore, isBonusMatched);
        }

        if (matchWinnings.size() == 2) {
            Winning matchWinning= matchWinnings.stream()
                    .filter(winning -> winning.isBonus() == isBonusMatched)
                    .toList().getFirst();
            matchWinning.countUpIfMatched(matchingScore, isBonusMatched);
        }
    }

    // 수익률 계산
    public double getYield(int lottoSize) {
        int purchaseAmount = this.price * lottoSize;

        int totalWinningMoney = winningRule.stream()
                .mapToInt(Winning::getTotalPrizeMoney)
                .sum();

        double yield = (double) totalWinningMoney / purchaseAmount * 100;
        return (double) Math.round(yield * 10)/10;
    }
}
