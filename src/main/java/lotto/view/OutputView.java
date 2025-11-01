package lotto.view;

import java.util.List;
import lotto.controller.dto.PurchaseResponse;

public class OutputView {
    public OutputView() {}

    public void printBill(List<PurchaseResponse> responseList) {
        int amount = responseList.size();
        System.out.printf("%d개를 구매했습니다.", amount);
        LottoView.nexLine();
        responseList.forEach(this::printLottoNumbers);
        LottoView.nexLine();
    }

    private void printLottoNumbers(PurchaseResponse response) {
        List<Integer> numbers = response.numbers();
        System.out.println(numbers);
    }
}
