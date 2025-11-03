package lotto.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class WinningNumbers {
    private static final int MIN = 1;
    private static final int MAX = 45;
    private static final int COUNT = 6;

    private final Set<Integer> main;

    private WinningNumbers(Set<Integer> main) {
        this.main = main;
    }

    public static WinningNumbers parse(String csv) {
        if (csv == null || csv.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호를 입력해 주세요.");
        }
        Set<Integer> nums = Arrays.stream(csv.split(","))
                .map(String::trim)
                .map(WinningNumbers::parseInt)
                .collect(Collectors.toCollection(HashSet::new));

        if (nums.size() != COUNT) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복되지 않은 6개여야 합니다.");
        }
        for (int n : nums) {
            if (n < MIN || n > MAX) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 1부터 45 사이여야 합니다.");
            }
        }
        return new WinningNumbers(nums);
    }

    public boolean contains(int n) {
        return main.contains(n);
    }

    public int matchCount(lotto.domain.Lotto ticket) {
        int cnt = 0;
        for (int n : ticket.getNumbers()) {
            if (main.contains(n)) {
                cnt++;
            }
        }
        return cnt;
    }

    private static int parseInt(String s) {
        if (s.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 비어있는 번호가 있습니다.");
        }
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력해 주세요.");
        }
    }
    public void validateBonus(int bonus) {
        if (bonus < 1 || bonus > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이여야 합니다.");
        }
        if (contains(bonus)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}

