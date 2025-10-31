package lotto.controller;

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
        purchaseAmount();
        winningNumber();
    }


    public void purchaseAmount() {
        // 구입금액 입력

        // 구입금액 넘기고 로또 발행결과 받기

        // 로또 발행결과 출력
    }

    public void winningNumber() {
        // 당첨 번호 및 보너스 번호 입력

        // 번호 넘기고 로또 결과 받기

        // 로또 결과 출력
    }
}
