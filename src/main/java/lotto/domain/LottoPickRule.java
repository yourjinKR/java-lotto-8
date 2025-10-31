package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoPickRule implements PickRule<List<Integer>> {
    private final int startInclusive;
    private final int endInclusive;
    private final int count;

    public LottoPickRule(int startInclusive, int endInclusive, int count) {
        this.startInclusive = startInclusive;
        this.endInclusive = endInclusive;
        this.count = count;
    }

    @Override
    public List<Integer> pickNumber() {
        return Randoms.pickUniqueNumbersInRange(startInclusive, endInclusive, count);
    }
}
