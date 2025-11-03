package lotto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class ProfitCalculator {
    private ProfitCalculator() {}

    public static double rate(long totalReward, long amountSpent) {
        if (amountSpent <= 0) {
            return 0.0;
        }
        BigDecimal reward = new BigDecimal(totalReward);
        BigDecimal spent = new BigDecimal(amountSpent);
        BigDecimal hundred = new BigDecimal("100");
        BigDecimal percent = reward
                .divide(spent, 10, RoundingMode.HALF_UP)
                .multiply(hundred)
                .setScale(1, RoundingMode.HALF_UP);
        return percent.doubleValue();
    }
}
