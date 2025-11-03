package lotto.view;

import java.util.List;
import lotto.domain.Lotto;
import lotto.service.LottoIssuer;

public final class OutputView {
    private OutputView() {}

    public static void printError(String message) {
        if (message == null || message.isBlank()) {
            System.out.println("[ERROR] 잘못된 입력입니다.");
            return;
        }
        if (message.startsWith("[ERROR]")) {
            System.out.println(message);
            return;
        }
        System.out.println("[ERROR] " + message);
    }

    public static void printPurchasedCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printTickets(List<Lotto> tickets) {
        for (Lotto ticket : tickets) {
            List<Integer> sorted = LottoIssuer.sortedNumbers(ticket);
            System.out.println(sorted);
        }
    }

    public static void debug(String s) {
        System.out.println(s);
    }
}
