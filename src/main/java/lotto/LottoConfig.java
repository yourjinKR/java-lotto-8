package lotto;

import java.util.List;
import lotto.domain.LottoPickRule;
import lotto.domain.PlayRule;
import lotto.domain.Winning;

public class LottoConfig {
    public static final int START_INCLUSIVE = 1;
    public static final int END_INCLUSIVE = 45;
    public static final int COUNT = 6;
    public static final int PRICE = 1_000;

    // 로또 규칙 세팅
    public static PlayRule getDefaultLottoRule() {
        return new PlayRule(
                PRICE,
                getDefaultLottoPickRule(),
                getDefaultWinningRule()
        );
    }

    // 추첨 규칙 세팅
    public static LottoPickRule getDefaultLottoPickRule() {
        return new LottoPickRule(
                LottoConfig.START_INCLUSIVE,
                LottoConfig.END_INCLUSIVE,
                LottoConfig.COUNT);
    }

    // 당첨 기준과 상금을 아래에 세팅
    public static List<Winning> getDefaultWinningRule() {
        return List.of(
                new Winning(3, false, 5_000),
                new Winning(4, false, 50_000),
                new Winning(5, false, 1_500_000),
                new Winning(5, true, 30_000_000),
                new Winning(6, false, 2_000_000_000)
        );
    }
}
