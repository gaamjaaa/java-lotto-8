package lotto;

import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    private static final int PRICE_PER_TICKET = 1_000;

    public static void main(String[] args) {
        int amount = readValidAmount();
        int ticketCount = amount / PRICE_PER_TICKET;
        OutputView.debug("구매 장수: " + ticketCount);
        // 이후 단계에서 발행/출력, 당첨 입력 추가
    }

    private static int readValidAmount() {
        while (true) {
            try {
                int amount = InputView.readPurchaseAmount();
                validateAmount(amount);
                return amount;
            } catch (IllegalArgumentException e) {
                lotto.view.OutputView.printError(e.getMessage());
            }
        }
    }

    private static void validateAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 양의 정수여야 합니다.");
        }
        if (amount % PRICE_PER_TICKET != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }
    }
}