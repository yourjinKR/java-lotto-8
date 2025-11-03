package lotto.view;

import java.text.MessageFormat;
import java.util.List;
import lotto.dto.PurchaseResponse;
import lotto.dto.ResultResponse;
import lotto.dto.WinningResponse;

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
        List<Integer> sortedNumbers = numbers.stream().sorted().toList();

        System.out.println(sortedNumbers);
    }

    public void printLottoResult(ResultResponse response) {
        System.out.println("당첨 통계");
        System.out.println("---");

        List<WinningResponse> winningResponseList = response.winningResponseList();
        winningResponseList.forEach(this::printWinningResult);

        String yield = MessageFormat.format("총 수익률은 {0}%입니다.", response.yield());
        System.out.println(yield);
    }

    private void printWinningResult(WinningResponse response) {
        String bonusMention = bonusMention(response.bonus());

        String result = MessageFormat.format(
                "{0}개 일치{1} ({2}원) - {3}개",
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
