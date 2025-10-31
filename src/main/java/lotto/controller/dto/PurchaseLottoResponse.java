package lotto.controller.dto;

import java.util.List;

public record PurchaseLottoResponse(
        List<Integer> numbers
) {
}
