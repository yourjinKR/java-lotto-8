package lotto.controller.dto;

public record WinningLottoResponse(
        int score,
        boolean bonus,
        int prizeMoney,
        int matchedAmount
) {

}
