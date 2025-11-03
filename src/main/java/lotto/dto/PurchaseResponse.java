package lotto.dto;

import java.util.List;

public record PurchaseResponse(
        List<Integer> numbers
) {
}
