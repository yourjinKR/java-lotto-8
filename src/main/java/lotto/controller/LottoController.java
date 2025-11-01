package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.LottoConfig;
import lotto.controller.dto.PurchaseRequest;
import lotto.controller.dto.ResultRequest;
import lotto.controller.dto.ResultResponse;
import lotto.domain.LottoRule;
import lotto.controller.dto.PurchaseResponse;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    InputView inputView;
    OutputView outputView;
    LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        LottoRule lottoRule = LottoConfig.getDefaultLottoRule();

        purchaseAmount(lottoRule);
        winningNumber(lottoRule);
        Console.close();
    }


    public void purchaseAmount(LottoRule lottoRule) {
        while (true) {
            try {
                PurchaseRequest request = inputView.inputPurchaseAmount();
                List<PurchaseResponse> responseList = lottoService.createByPurchaseAmount(request, lottoRule);
                outputView.printBill(responseList);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void winningNumber(LottoRule lottoRule) {
        while (true) {
            try {
                ResultRequest request = inputView.inputWinningNumber();
                ResultResponse response = lottoService.getResult(request, lottoRule);
                outputView.printLottoResult(response);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
