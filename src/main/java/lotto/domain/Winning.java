package lotto.domain;

public class Winning {
    private final int score;
    private final boolean bonus;
    private final int prizeMoney;
    private int matchedCount;

    public Winning(int score, boolean bonus, int prizeMoney) {
        this.score = score;
        this.bonus = bonus;
        this.prizeMoney = prizeMoney;
        this.matchedCount = 0;
    }

    public int getScore() {
        return score;
    }

    public boolean isBonus() {
        return bonus;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getMatchedCount() {
        return matchedCount;
    }

    public boolean isMatched(int matchingScore, boolean isBonusMatched) {
        return (this.score == matchingScore && this.bonus == isBonusMatched);
    }

    public void countUpIfMatched(int matchingScore) {
        if (this.score == matchingScore) {
            matchedCount++;
        }
    }

    public void countUpIfMatched(int matchingScore, boolean isBonusMatched) {
        if (this.score == matchingScore && this.bonus == isBonusMatched) {
            matchedCount++;
        }
    }

    public int getTotalPrizeMoney() {
        return this.prizeMoney * this.matchedCount;
    }
}
