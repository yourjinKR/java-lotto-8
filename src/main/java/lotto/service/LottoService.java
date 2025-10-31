package lotto.service;

import java.util.List;
import lotto.controller.dto.PurchaseLottoRequest;
import lotto.domain.Lotto;
import lotto.domain.LottoRule;
import lotto.domain.PickRule;
import lotto.controller.dto.PurchaseLottoResponse;
import lotto.mapper.LottoMapper;
import lotto.repository.LottoRepository;
import lotto.util.ParseUtil;

public class LottoService {
    public final LottoRepository lottoRepository;
    public final LottoMapper lottoMapper;

    public LottoService(LottoRepository lottoRepository, LottoMapper lottoMapper) {
        this.lottoRepository = lottoRepository;
        this.lottoMapper = lottoMapper;
    }

    public List<PurchaseLottoResponse> createByPurchaseAmount(PurchaseLottoRequest request, LottoRule lottoRule) {
        int purchaseAmount = ParseUtil.parseInt(request.purchaseAmount());

        int amount = lottoRule.getPickableChance(purchaseAmount);
        PickRule<List<Integer>> pickRule = lottoRule.getPickRule();

        List<Lotto> lottoList = lottoRepository.registerAsAmountByRule(amount, pickRule);

        return lottoMapper.toDto(lottoList);
    }
}
