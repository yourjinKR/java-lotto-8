package lotto.service;

import lotto.repository.LottoRepository;

public class LottoService {
    public final LottoRepository lottoRepository;

    public LottoService(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }
}
