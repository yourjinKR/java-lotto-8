package lotto.domain;

public class Winning {
    private final int score;
    private final boolean bonus;
    private final int prizeMoney;

    public Winning(int score, boolean bonus, int prizeMoney) {
        this.score = score;
        this.bonus = bonus;
        this.prizeMoney = prizeMoney;
    }
}
