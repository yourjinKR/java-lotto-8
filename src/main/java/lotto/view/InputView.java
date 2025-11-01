package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.controller.dto.PurchaseRequest;
import lotto.controller.dto.WinningRequest;

public class InputView {
    public InputView() {}

    public PurchaseRequest inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String purchaseAmount = Console.readLine();
        LottoView.nexLine();

        return new PurchaseRequest(purchaseAmount);
    }

    public WinningRequest inputWinningNumber() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String winningNumber = Console.readLine();
        LottoView.nexLine();

        System.out.println("보너스 번호를 입력해 주세요.");
        String bonusNumber = Console.readLine();
        LottoView.nexLine();

        return new WinningRequest(winningNumber, bonusNumber);
    }
}
