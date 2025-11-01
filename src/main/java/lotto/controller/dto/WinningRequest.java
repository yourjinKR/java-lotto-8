package lotto.controller.dto;

public record WinningRequest(
        String winningNumber,
        String bonusNumber
) {
}
