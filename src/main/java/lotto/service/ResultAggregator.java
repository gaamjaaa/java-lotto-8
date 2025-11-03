package lotto.service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;

public final class ResultAggregator {
    public Map<Rank, Integer> aggregate(List<Lotto> tickets, WinningNumbers winning, int bonus) {
        Map<Rank, Integer> counts = initCounts();
        for (Lotto t : tickets) {
            int match = winning.matchCount(t);
            boolean bonusMatch = contains(t, bonus);
            Rank rank = Rank.of(match, bonusMatch);
            add(counts, rank);
        }
        return counts;
    }

    public long totalReward(Map<Rank, Integer> counts) {
        long sum = 0L;
        for (Rank r : counts.keySet()) {
            sum += r.reward() * counts.get(r);
        }
        return sum;
    }

    private Map<Rank, Integer> initCounts() {
        Map<Rank, Integer> m = new EnumMap<>(Rank.class);
        for (Rank r : Rank.values()) {
            m.put(r, 0);
        }
        return m;
    }

    private void add(Map<Rank, Integer> m, Rank r) {
        if (r == null) {
            return;
        }
        m.put(r, m.get(r) + 1);
    }

    private boolean contains(Lotto t, int n) {
        for (int v : t.getNumbers()) {
            if (v == n) {
                return true;
            }
        }
        return false;
    }
}
