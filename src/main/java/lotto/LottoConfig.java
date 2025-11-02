package lotto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoPickRule;
import lotto.domain.PlayRule;
import lotto.domain.Winning;

public class LottoConfig {
    // 로또 규칙 세팅
    public static PlayRule getDefaultLottoRule() {
        return new PlayRule(
                getDefaultLottoPickRule(),
                getDefaultWinningRule()
        );
    }

    // 추첨 규칙 세팅
    private static LottoPickRule getDefaultLottoPickRule() {
        return new LottoPickRule(
                Lotto.START_INCLUSIVE,
                Lotto.END_INCLUSIVE,
                Lotto.COUNT);
    }

    // 당첨 기준과 상금을 아래에 세팅
    private static List<Winning> getDefaultWinningRule() {
        return List.of(
                new Winning(3, false, 5_000),
                new Winning(4, false, 50_000),
                new Winning(5, false, 1_500_000),
                new Winning(5, true, 30_000_000),
                new Winning(6, false, 2_000_000_000)
        );
    }
}
