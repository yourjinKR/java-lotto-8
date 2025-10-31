package lotto.dto;

import java.util.List;

public record LottoCreateResponse(
        List<Integer> numbers
) {
}
