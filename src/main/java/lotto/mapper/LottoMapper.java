package lotto.mapper;

import java.util.List;
import lotto.domain.Lotto;
import lotto.controller.dto.PurchaseResponse;

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
}
