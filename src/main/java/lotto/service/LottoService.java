package lotto.service;

import java.util.List;
import lotto.controller.dto.PurchaseRequest;
import lotto.controller.dto.ResultRequest;
import lotto.controller.dto.ResultResponse;
import lotto.domain.Lotto;
import lotto.domain.LottoRule;
import lotto.domain.PickRule;
import lotto.controller.dto.PurchaseResponse;
import lotto.domain.Winning;
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

    public List<PurchaseResponse> createByPurchaseAmount(PurchaseRequest request, LottoRule lottoRule) {
        int purchaseAmount = ParseUtil.parseInt(request.purchaseAmount());

        int amount = lottoRule.getPickableChance(purchaseAmount);
        PickRule<List<Integer>> pickRule = lottoRule.getPickRule();

        List<Lotto> lottoList = lottoRepository.createAsAmountByRule(amount, pickRule);

        return lottoMapper.toDto(lottoList);
    }

    public ResultResponse getResult(ResultRequest request, LottoRule lottoRule) {
        List<Integer> winningNumbers = ParseUtil.parseIntListByDelimiter(request.winningNumber(), ",");
        int bonusNumber = ParseUtil.parseInt(request.bonusNumber());

        List<Lotto> lottoList = lottoRepository.findAll();
        lottoList.forEach(lotto -> matchWinningResult(winningNumbers, bonusNumber, lotto, lottoRule));

        List<Winning> winningRule = lottoRule.getWinningRule();

        int lottoSize = lottoList.size();
        double yield = lottoRule.getYield(lottoSize);

        return lottoMapper.toDto(winningRule, yield);
    }

    public void matchWinningResult(List<Integer> winningNumbers, int bonusNumber, Lotto lotto, LottoRule lottoRule) {
        int matchingScore = lotto.getMatchingScore(winningNumbers);
        boolean isBonusMatched = lotto.isBonusMatched(bonusNumber);

        lottoRule.matchWinningRule(matchingScore, isBonusMatched);
    }
}
