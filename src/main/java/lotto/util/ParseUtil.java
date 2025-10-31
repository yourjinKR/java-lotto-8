package lotto.util;

import java.util.Arrays;
import java.util.List;

public class ParseUtil {
    public static int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT_NUMBER.getMessage());
        }
    }

    public static List<Integer> parseIntListByDelimiter(String value, String delimiter) {
        return Arrays.stream(value.split(delimiter))
                .map(ParseUtil::parseInt)
                .toList();
    }
}
