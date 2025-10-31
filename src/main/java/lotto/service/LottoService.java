package lotto.service;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoRule;
import lotto.domain.PickRule;
import lotto.controller.dto.LottoCreateResponse;
import lotto.mapper.LottoMapper;
import lotto.repository.LottoRepository;

public class LottoService {
    public final LottoRepository lottoRepository;
    public final LottoMapper lottoMapper;

    public LottoService(LottoRepository lottoRepository, LottoMapper lottoMapper) {
        this.lottoRepository = lottoRepository;
        this.lottoMapper = lottoMapper;
    }

    public List<LottoCreateResponse> createByPurchaseAmount(int purchaseAmount, LottoRule lottoRule) {
        int amount = lottoRule.getPickableChance(purchaseAmount);
        PickRule<List<Integer>> pickRule = lottoRule.getPickRule();

        List<Lotto> lottoList = lottoRepository.registerAsAmountByRule(amount, pickRule);

        return lottoMapper.toDto(lottoList);
    }
}
