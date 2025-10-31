package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.util.ErrorMessage;

public class LottoPickRule implements PickRule<List<Integer>> {
    private final int startInclusive;
    private final int endInclusive;
    private final int count;

    public LottoPickRule(int startInclusive, int endInclusive, int count) {
        validate(startInclusive, endInclusive, count);
        this.startInclusive = startInclusive;
        this.endInclusive = endInclusive;
        this.count = count;
    }

    private void validate(int startInclusive, int endInclusive, int count) {
        int gap = endInclusive - startInclusive;
        if (gap+1 < count) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE_NUMBER.getMessage());
        }
    }

    @Override
    public List<Integer> pick() {
        return Randoms.pickUniqueNumbersInRange(startInclusive, endInclusive, count);
    }
}
