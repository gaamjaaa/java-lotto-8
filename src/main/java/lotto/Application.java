package lotto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.WinningNumbers;
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

        OutputView.printPurchasedCount(ticketCount);
        OutputView.printTickets(tickets);

        WinningNumbers winning = readValidWinningNumbers();
        int bonus = readValidBonus(winning);

        // 다음 커밋: 등수/통계 계산 및 출력
        OutputView.debug("보너스: " + bonus);
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

    private static WinningNumbers readValidWinningNumbers() {
        while (true) {
            try {
                String line = InputView.readWinningNumbersLine();
                return WinningNumbers.parse(line);
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private static int readValidBonus(WinningNumbers winning) {
        while (true) {
            try {
                int bonus = InputView.readBonusNumber();
                winning.validateBonus(bonus);
                return bonus;
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
