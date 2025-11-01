package lotto.controller.dto;

public record WinningResponse(
        int score,
        boolean bonus,
        int prizeMoney,
        int matchedAmount
) {

}
