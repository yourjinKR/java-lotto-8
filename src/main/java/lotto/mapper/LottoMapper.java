package lotto.mapper;

import java.util.List;
import lotto.controller.dto.ResultResponse;
import lotto.controller.dto.WinningResponse;
import lotto.domain.Lotto;
import lotto.controller.dto.PurchaseResponse;
import lotto.domain.Winning;

public class LottoMapper {
    public List<PurchaseResponse> toDto(List<Lotto> lottoList) {
        return lottoList.stream()
                .map(this::toDto)
                .toList();
    }

    private PurchaseResponse toDto(Lotto lotto) {
        List<Integer> numbers = lotto.getNumbers();
        return new PurchaseResponse(numbers);
    }

    public ResultResponse toDto(List<Winning> winningRule, double yield) {
        List<WinningResponse> winningDtoList = winningRule.stream()
                .map(this::toDto)
                .toList();

        return new ResultResponse(winningDtoList, yield);
    }

    private WinningResponse toDto(Winning winningRule) {
        return new WinningResponse(
                winningRule.getScore(),
                winningRule.isBonus(),
                winningRule.getPrizeMoney(),
                winningRule.getMatchedCount()
        );
    }
}
