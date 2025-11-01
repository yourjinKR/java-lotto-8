package lotto.domain;

import java.util.List;
import lotto.LottoConfig;
import lotto.util.ErrorMessage;

public class WinningLotto extends Lotto implements HasBonusNumber<Integer> {
    private final int bonsNumber;

    public WinningLotto(List<Integer> numbers, int bonsNumber) {
        super(numbers);
        validateBonusNumber(bonsNumber);
        this.bonsNumber = bonsNumber;
    }

    @Override
    public Integer getBonusNumber() {
        return bonsNumber;
    }

    private void validateBonusNumber(int bonsNumber) {
        if ((bonsNumber < LottoConfig.START_INCLUSIVE) || (bonsNumber > LottoConfig.END_INCLUSIVE)) {
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_RANGE_NUMBER.getMessage());
        }
    }
}
