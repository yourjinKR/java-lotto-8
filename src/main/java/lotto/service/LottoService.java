package lotto.service;

import java.util.List;
import lotto.controller.dto.PurchaseRequest;
import lotto.controller.dto.ResultRequest;
import lotto.controller.dto.ResultResponse;
import lotto.domain.Lotto;
import lotto.domain.PlayRule;
import lotto.domain.PickRule;
import lotto.controller.dto.PurchaseResponse;
import lotto.domain.Winning;
import lotto.domain.WinningLotto;
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

    public List<PurchaseResponse> createByPurchaseAmount(PurchaseRequest request, PlayRule playRule) {
        int purchaseAmount = ParseUtil.parseInt(request.purchaseAmount());

        int amount = playRule.getPickableChance(purchaseAmount);
        PickRule<List<Integer>> pickRule = playRule.getPickRule();

        List<Lotto> lottoList = lottoRepository.createAsAmountByRule(amount, pickRule);

        return lottoMapper.toDto(lottoList);
    }

    public ResultResponse getResult(ResultRequest request, PlayRule playRule) {
        WinningLotto winningLotto = lottoMapper.toEntity(request);

        List<Lotto> lottoList = lottoRepository.findAll();
        lottoList.forEach(lotto -> matchWinningResult(winningLotto, lotto, playRule));

        List<Winning> winningRule = playRule.getWinningRule();

        double yield = playRule.getYield(lottoList);

        return lottoMapper.toDto(winningRule, yield);
    }

    public void matchWinningResult(WinningLotto winningLotto, Lotto lotto, PlayRule playRule) {
        int matchingScore = lotto.getMatchingScore(winningLotto);
        boolean isBonusMatched = lotto.isBonusMatched(winningLotto);

        playRule.matchWinningRule(matchingScore, isBonusMatched);
    }
}
