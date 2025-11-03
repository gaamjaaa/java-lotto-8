package lotto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.service.LottoIssuer;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    private static final int PRICE_PER_TICKET = 1_000;

    public static void main(String[] args) {
        int amount = readValidAmount();
        int ticketCount = amount / PRICE_PER_TICKET;

        LottoIssuer issuer = new LottoIssuer();
        List<Lotto> tickets = issuer.issue(ticketCount);

        // 다음 커밋에서 출력 추가
        OutputView.debug("발행 완료: " + tickets.size() + "장");
    }

    private static int readValidAmount() {
        while (true) {
            try {
                int amount = InputView.readPurchaseAmount();
                validateAmount(amount);
                return amount;
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
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
