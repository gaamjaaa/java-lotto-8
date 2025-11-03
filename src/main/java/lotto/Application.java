package lotto;

import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        int amount = InputView.readPurchaseAmount();
        // 이후 단계에서 검증/발행/출력 추가
        OutputView.debug("입력받은 금액: " + amount);
    }
}
