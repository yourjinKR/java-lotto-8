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
        // 구입금액 입력
        PurchaseLottoRequest request = inputView.inputPurchaseAmount();

        // 구입금액 넘기고 로또 발행결과 받기
        List<PurchaseLottoResponse> responseList = lottoService.createByPurchaseAmount(request, lottoRule);

        // 로또 발행결과 출력
        outputView.printBill(responseList);
    }

    public void winningNumber() {
        // 당첨 번호 및 보너스 번호 입력

        // 번호 넘기고 로또 결과 받기

        // 로또 결과 출력
    }
}
