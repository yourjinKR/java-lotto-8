package lotto.mapper;

import java.util.List;
import lotto.domain.Lotto;
import lotto.controller.dto.PurchaseLottoResponse;

public class LottoMapper {
    public List<PurchaseLottoResponse> toDto(List<Lotto> lottoList) {
        return lottoList.stream()
                .map(this::toDto)
                .toList();
    }

    private PurchaseLottoResponse toDto(Lotto lotto) {
        List<Integer> numbers = lotto.getNumbers();
        return new PurchaseLottoResponse(numbers);
    }
}
