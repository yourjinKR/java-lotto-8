package lotto.controller.dto;

import java.util.List;

public record ResultResponse(
        List<WinningResponse> winningResponseList,
        double yield
) {
}
