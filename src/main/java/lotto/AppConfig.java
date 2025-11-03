package lotto;

import lotto.controller.LottoController;
import lotto.dto.mapper.LottoMapper;
import lotto.domain.repository.LottoRepository;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LottoService lottoService = new LottoService(new LottoRepository(), new LottoMapper());

    private final LottoController lottoController = new LottoController(inputView, outputView, lottoService);

    public LottoController lottoController() {
        return lottoController;
    }
}
