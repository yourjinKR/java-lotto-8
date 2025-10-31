package lotto.util;

import java.text.MessageFormat;

public enum ErrorMessage {

    INVALID_FORMAT_NUMBER("숫자를 입력해주세요"),
    INVALID_RANGE_NUMBER("올바른 범위의 값을 입력해주세요"),
    OUT_OF_RANGE_NUMBER("범위 내 숫자를 입력해주세요"),
    DUPLICATE_LOTTO_NUMBER("로또 내 중복되는 번호가 있어요"),
    INVALID_INPUT_MONEY("올바르지 않은 구입금액이에요"),
    UNMATCH_WINNING_AMOUNT("알맞은 수의 당첨번호를 제시해주세요")
    ;


    private final String message;
    private static final String PREFIX = "[ERROR]";

    ErrorMessage(String message) {
        this.message = MessageFormat.format("{0} {1}", PREFIX, message);
    }

    public String getMessage() {
        return this.message;
    }
}
