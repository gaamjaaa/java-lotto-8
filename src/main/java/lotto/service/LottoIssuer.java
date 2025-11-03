package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.Lotto;

public final class LottoIssuer {
    private static final int MIN = 1;
    private static final int MAX = 45;
    private static final int COUNT = 6;

    public List<Lotto> issue(int ticketCount) {
        List<Lotto> tickets = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            List<Integer> nums = Randoms.pickUniqueNumbersInRange(MIN, MAX, COUNT);
            tickets.add(new Lotto(nums));
        }
        return tickets;
    }

    // 출력용 정렬 도우미
    public static List<Integer> sortedNumbers(Lotto lotto) {
        List<Integer> copy = new ArrayList<>(lotto.getNumbers());
        Collections.sort(copy);
        return copy;
    }
}
