package lotto.view;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import lotto.domain.Rank;

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

    public static void printTickets(java.util.List<lotto.domain.Lotto> tickets) {
        for (lotto.domain.Lotto ticket : tickets) {
            java.util.List<Integer> sorted = lotto.service.LottoIssuer.sortedNumbers(ticket);
            System.out.println(sorted);
        }
    }

    public static void printStatistics(Map<Rank, Integer> counts) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        printLine(Rank.FIFTH, counts.get(Rank.FIFTH));   // 3개
        printLine(Rank.FOURTH, counts.get(Rank.FOURTH)); // 4개
        printLine(Rank.THIRD, counts.get(Rank.THIRD));   // 5개
        printLine(Rank.SECOND, counts.get(Rank.SECOND)); // 5개+보너스
        printLine(Rank.FIRST, counts.get(Rank.FIRST));   // 6개
    }

    private static void printLine(Rank rank, int count) {
        String won = formatWon(rank.reward());
        System.out.println(rank.label() + " (" + won + ") - " + count + "개");
    }

    private static String formatWon(long v) {
        NumberFormat nf = NumberFormat.getInstance(Locale.KOREA);
        return nf.format(v) + "원";
    }

    public static void printProfitRate(double rate) {
        System.out.println("총 수익률은 " + rate + "%입니다.");
    }

    public static void debug(String s) {
        System.out.println(s);
    }
}
