package com.whfrp3.model.enums;

/**
 * Money types.
 */
public enum MoneyType {

    /**
     * Brass coin.
     */
    BRASS(25),

    /**
     * Silver coin.
     */
    SILVER(100),

    /**
     * Gold coin.
     */
    GOLD(0);

    /**
     * Max amount of the money type.
     */
    private final int maxAmount;

    /**
     * Constructor.
     *
     * @param maxAmount Max amount of the money type.
     */
    MoneyType(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    /**
     * Getter of max amount.
     *
     * @return Max amount of money type.
     */
    public int getMaxAmount() {
        return this.maxAmount;
    }

    /**
     * Return superior money type.
     *
     * @return Superior money type.
     */
    public MoneyType getSuperiorMoneyType() {
        switch (this) {
            case BRASS:
                return SILVER;
            case SILVER:
                return GOLD;

            default:
                return GOLD;
        }
    }
}
