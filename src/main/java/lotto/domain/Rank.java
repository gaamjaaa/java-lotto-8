package lotto.domain;

public enum Rank {
    FIRST(6, true, true, 2_000_000_000L, "6개 일치"),
    SECOND(5, true, true, 30_000_000L, "5개 일치, 보너스 볼 일치"),
    THIRD(5, true, false, 1_500_000L, "5개 일치"),
    FOURTH(4, false, false, 50_000L, "4개 일치"),
    FIFTH(3, false, false, 5_000L, "3개 일치"),
    NONE(0, false, false, 0L, "");

    private final int matchCount;
    private final boolean usesFiveRule;
    private final boolean bonusRequired;
    private final long reward;
    private final String label;

    Rank(int matchCount, boolean usesFiveRule, boolean bonusRequired, long reward, String label) {
        this.matchCount = matchCount;
        this.usesFiveRule = usesFiveRule;
        this.bonusRequired = bonusRequired;
        this.reward = reward;
        this.label = label;
    }

    public static Rank of(int matchCount, boolean bonusMatch) {
        if (matchCount == 6) {
            return FIRST;
        }
        if (matchCount == 5) {
            return bonusMatch ? SECOND : THIRD;
        }
        if (matchCount == 4) {
            return FOURTH;
        }
        if (matchCount == 3) {
            return FIFTH;
        }
        return NONE;
    }

    public long reward() {
        return reward;
    }

    public String label() {
        return label;
    }

    public boolean printable() {
        return this != NONE;
    }
}
