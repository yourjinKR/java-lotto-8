package lotto.controller.dto;

import java.util.List;

public record PurchaseResponse(
        List<Integer> numbers
) {
}
