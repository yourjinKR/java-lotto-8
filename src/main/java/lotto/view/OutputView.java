package lotto.view;

import java.text.MessageFormat;
import java.util.List;
import lotto.controller.dto.PurchaseResponse;
import lotto.controller.dto.ResultResponse;
import lotto.controller.dto.WinningResponse;

public class OutputView {
    public OutputView() {}

    public void printBill(List<PurchaseResponse> responseList) {
        int amount = responseList.size();
        System.out.printf("%d개를 구매했습니다.", amount);
        LottoView.nexLine();
        responseList.forEach(this::printLottoNumbers);
        LottoView.nexLine();
    }

    private void printLottoNumbers(PurchaseResponse response) {
        List<Integer> numbers = response.numbers();
        System.out.println(numbers);
    }

    public void printLottoResult(ResultResponse response) {
        System.out.println("당첨 통계");
        System.out.println("---");

        List<WinningResponse> winningResponseList = response.winningResponseList();
        winningResponseList.forEach(this::printWinningResult);

        String yield = MessageFormat.format("{0}%입니다.", response.yield());
        System.out.println();
    }

    private void printWinningResult(WinningResponse response) {
        String bonusMention = bonusMention(response.bonus());

        String result = MessageFormat.format(
                "{0}개 일치{1} ({2}) - {3}개",
                response.score(),
                bonusMention,
                response.prizeMoney(),
                response.matchedAmount()
        );

        System.out.println(result);
    }

    private String bonusMention(boolean bonus) {
        if (bonus) {
            return ", 보너스 볼 일치";
        }

        return "";
    }
}
