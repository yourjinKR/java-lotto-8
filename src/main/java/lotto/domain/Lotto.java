package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import lotto.util.ErrorMessage;

public class Lotto {
    public static final int START_INCLUSIVE = 1;
    public static final int END_INCLUSIVE = 45;
    public static final int COUNT = 6;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public static Lotto pickRuleFrom(PickRule<List<Integer>> pickRule) {
        List<Integer> numbers = pickRule.pick();
        return new Lotto(numbers);
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        validateRange(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != COUNT) {
            throw new IllegalArgumentException(ErrorMessage.UNMATCH_WINNING_AMOUNT.getMessage());
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        long numberUniqueSize = numbers.stream().distinct().count();

        if (numberUniqueSize != COUNT) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_LOTTO_NUMBER.getMessage());
        }
    }

    private void validateRange(List<Integer> numbers) {
        boolean outOfRange = numbers.stream()
                .anyMatch(number -> (number < START_INCLUSIVE) || (number > END_INCLUSIVE));

        if (outOfRange)
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_RANGE_NUMBER.getMessage());
    }

    public int getMatchingScore(WinningLotto winningLotto) {
        List<Integer> winningNumbers = winningLotto.getNumbers();

        List<Integer> matchList = numbers.stream()
                .filter(number -> winningNumbers.stream()
                        .anyMatch(Predicate.isEqual(number)))
                .toList();

        return matchList.size();
    }

    public boolean isBonusMatched(WinningLotto winningLotto) {
        int bonusNumber = winningLotto.getBonusNumber();
        return numbers.contains(bonusNumber);
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }
}
