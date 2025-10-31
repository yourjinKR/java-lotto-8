package lotto;

import lotto.controller.LottoController;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {
    public InputView inputView = new InputView();
    public OutputView outputView = new OutputView();

    public LottoController lottoController = new LottoController(inputView, outputView);

    public LottoController lottoController() {
        return lottoController;
    }
}
