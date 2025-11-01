package lotto.controller.dto;

public record WinningLottoRequest(
        String winningNumber,
        String bonusNumber
) {
}
