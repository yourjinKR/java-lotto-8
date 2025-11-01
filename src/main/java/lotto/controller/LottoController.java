package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.LottoConfig;
import lotto.controller.dto.PurchaseLottoRequest;
import lotto.domain.LottoRule;
import lotto.controller.dto.PurchaseLottoResponse;
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
        winningNumber();
        Console.close();
    }


    public void purchaseAmount(LottoRule lottoRule) {
        while (true) {
            try {
                PurchaseLottoRequest request = inputView.inputPurchaseAmount();
                List<PurchaseLottoResponse> responseList = lottoService.createByPurchaseAmount(request, lottoRule);
                outputView.printBill(responseList);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void winningNumber() {
        // 당첨 번호 및 보너스 번호 입력

        // 번호 넘기고 로또 결과 받기

        // 로또 결과 출력
    }
}
