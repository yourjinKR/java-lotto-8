package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.LottoConfig;
import lotto.dto.PurchaseRequest;
import lotto.dto.ResultRequest;
import lotto.dto.ResultResponse;
import lotto.domain.PlayRule;
import lotto.dto.PurchaseResponse;
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
        PlayRule playRule = LottoConfig.getDefaultLottoRule();

        purchaseAmount(playRule);
        winningNumber(playRule);
        Console.close();
    }


    public void purchaseAmount(PlayRule playRule) {
        while (true) {
            try {
                PurchaseRequest request = inputView.inputPurchaseAmount();
                List<PurchaseResponse> responseList = lottoService.createByPurchaseAmount(request, playRule);
                outputView.printBill(responseList);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void winningNumber(PlayRule playRule) {
        while (true) {
            try {
                ResultRequest request = inputView.inputWinningNumber();
                ResultResponse response = lottoService.getResult(request, playRule);
                outputView.printLottoResult(response);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
