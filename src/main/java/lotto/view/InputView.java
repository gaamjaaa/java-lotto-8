package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public final class InputView {
    private InputView() {}

    public static int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String raw = Console.readLine();
        return parseIntSafely(raw);
    }

    public static String readWinningNumbersLine() {
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
        return Console.readLine();
    }

    private static int parseIntSafely(String raw) {
        if (raw == null) {
            throw new IllegalArgumentException("[ERROR] 입력이 필요합니다.");
        }
        String trimmed = raw.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 입력이 필요합니다.");
        }
        try {
            return Integer.parseInt(trimmed);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해 주세요.");
        }
    }
}
