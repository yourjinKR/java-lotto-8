package lotto.service;

import java.util.List;
import lotto.dto.PurchaseRequest;
import lotto.dto.ResultRequest;
import lotto.dto.ResultResponse;
import lotto.domain.Lotto;
import lotto.domain.PlayRule;
import lotto.domain.PickRule;
import lotto.dto.PurchaseResponse;
import lotto.domain.Winning;
import lotto.domain.WinningLotto;
import lotto.dto.mapper.LottoMapper;
import lotto.domain.repository.LottoRepository;
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

        int chance = playRule.getPickableChance(purchaseAmount);
        PickRule<List<Integer>> pickRule = playRule.getPickRule();

        for (int i = 0; i < chance; i++) {
            Lotto lotto = Lotto.pickRuleFrom(pickRule);
            lottoRepository.save(lotto);
        }

        List<Lotto> lottoList = lottoRepository.findAll();

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
