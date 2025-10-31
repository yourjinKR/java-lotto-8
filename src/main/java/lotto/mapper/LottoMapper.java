package lotto.mapper;

import java.util.List;
import lotto.domain.Lotto;
import lotto.dto.LottoCreateResponse;

public class LottoMapper {
    public List<LottoCreateResponse> toDto(List<Lotto> lottoList) {
        return lottoList.stream()
                .map(this::toDto)
                .toList();
    }

    private LottoCreateResponse toDto(Lotto lotto) {
        List<Integer> numbers = lotto.getNumbers();
        return new LottoCreateResponse(numbers);
    }
}
