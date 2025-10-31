package lotto.domain;

import java.util.List;
import java.util.function.Predicate;
import lotto.util.ErrorMessage;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        validateRange(numbers);
    }

    // TODO : 상수값 리팩토링 필요 (로또 규칙에서 할당)
    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
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
        int LOWER_BOUND = 1;
        int UPPER_BOUND = 45;

        boolean outOfRange = numbers.stream()
                .anyMatch(number -> (number < LOWER_BOUND) || (number > UPPER_BOUND));

        if (outOfRange)
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_RANGE_NUMBER.getMessage());
    }

    public int getMatchingScore(List<Integer> winningNumbers) {
        List<Integer> matchList = numbers.stream()
                .filter(o -> winningNumbers.stream()
                        .anyMatch(Predicate.isEqual(o)))
                .toList();

        return matchList.size();
    }

    public boolean isBonusMatched(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

}
