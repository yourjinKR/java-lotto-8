package lotto.domain;

import java.util.List;
import java.util.function.Predicate;
import lotto.LottoConfig;
import lotto.util.ErrorMessage;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        validateRange(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LottoConfig.COUNT) {
            throw new IllegalArgumentException(ErrorMessage.UNMATCH_WINNING_AMOUNT.getMessage());
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        long numberSize = numbers.size();
        long numberUniqueSize = numbers.stream().distinct().count();

        if (numberSize != numberUniqueSize) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_LOTTO_NUMBER.getMessage());
        }
    }

    private void validateRange(List<Integer> numbers) {
        boolean outOfRange = numbers.stream()
                .anyMatch(number -> (number < LottoConfig.START_INCLUSIVE) || (number > LottoConfig.END_INCLUSIVE));

        if (outOfRange)
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_RANGE_NUMBER.getMessage());
    }

    public int getMatchingScore(List<Integer> winningNumbers) {
        List<Integer> matchList = numbers.stream()
                .filter(number -> winningNumbers.stream()
                        .anyMatch(Predicate.isEqual(number)))
                .toList();

        return matchList.size();
    }

    public boolean isBonusMatched(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

}
