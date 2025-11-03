package lotto.view;

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

    // 개발 진행 중 출력 확인용(제출 전 남겨도 무방)
    public static void debug(String s) {
        System.out.println(s);
    }
}
