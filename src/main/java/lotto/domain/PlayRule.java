package lotto.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.util.ErrorMessage;

// TODO : Map으로 관리
public class PlayRule {
    public static final int PRICE = 1_000;
    private final PickRule<List<Integer>> pickRule;
    private final List<Winning> winningRule;
    private final Map<Integer, List<Winning>> winningRuleSet = new HashMap<>();

    public PlayRule(PickRule<List<Integer>> pickRule, List<Winning> winningRule) {
        this.pickRule = pickRule;
        this.winningRule = winningRule;

        winningRule.forEach(winning -> {
            winningRuleSet.computeIfAbsent(winning.getScore(), key -> new ArrayList<>()).add(winning);
        });
    }

    public PickRule<List<Integer>> getPickRule() {
        return pickRule;
    }

    public List<Winning> getWinningRule() {
        return winningRule;
    }

    public int getPickableChance(int purchaseAmount) {
        if (purchaseAmount % PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_MONEY.getMessage());
        }
        return purchaseAmount / PRICE;
    }

    public void matchWinningRule(int matchingScore, boolean isBonusMatched) {
        List<Winning> matchWinnings = winningRule.stream()
                .filter(winning -> winning.getScore() == matchingScore)
                .toList();

        if (matchWinnings.size() == 1) {
            Winning matchWinning = matchWinnings.getFirst();
            matchWinning.countUpIfMatched(matchingScore);
        }

        if (matchWinnings.size() > 1) {
            matchWinnings.forEach(winning -> winning.countUpIfMatched(matchingScore, isBonusMatched));
        }
    }

    /*
        당첨 조건을 점수별로 매핑하여 관리
        해당 점수와 일치하는 당첨 조건이 1개일 경우는 보너스 일치 여부와 상관 없음
        그러나 같은 점수에 보너스 일치 여부에 따라 달라질 경우는 보너스 일치 여부까지 비교
     */
    public void matchWinningRuleV2(int matchingScore, boolean isBonusMatched) {
        List<Winning> winningList = winningRuleSet.get(matchingScore);

        if (winningList == null) return;

        if (winningList.size() == 1) {
            Winning matchWinning = winningList.getFirst();
            matchWinning.countUpIfMatched(matchingScore);
        }

        if (winningList.size() > 1) {
            winningList.forEach(winning -> winning.countUpIfMatched(matchingScore, isBonusMatched));
        }
    }

    // 수익률 계산
    public double getYield(List<Lotto> lottoList) {
        int lottoSize = lottoList.size();
        int purchaseAmount = PRICE * lottoSize;

        int totalWinningMoney = winningRule.stream()
                .mapToInt(Winning::getTotalPrizeMoney)
                .sum();

        double yield = (double) totalWinningMoney / purchaseAmount * 100;
        return (double) Math.round(yield * 10)/10;
    }
}
