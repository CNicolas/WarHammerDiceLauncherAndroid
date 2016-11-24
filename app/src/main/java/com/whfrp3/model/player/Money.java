package com.whfrp3.model.player;

import com.whfrp3.model.enums.MoneyType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class used for money management.
 */
public class Money {

    //region Properties

    /**
     * Money amounts.
     */
    private Map<MoneyType, Integer> amounts = new HashMap<>();

    //endregion

    //region Constructor

    /**
     * Constructor.
     *
     * @param goldAmount   Gold amount.
     * @param silverAmount Silver amount.
     * @param brassAmount  Brass amount.
     */
    public Money(int goldAmount, int silverAmount, int brassAmount) {
        amounts.put(MoneyType.BRASS, brassAmount);
        amounts.put(MoneyType.SILVER, silverAmount);
        amounts.put(MoneyType.GOLD, goldAmount);
    }

    //endregion

    /**
     * Return the amount of the given money type.
     *
     * @param type Money type.
     * @return Amount of the given money type.
     */
    public int getAmount(MoneyType type) {
        return amounts.get(type);
    }

    /**
     * Add given amount of money.
     *
     * @param amount Amount of money to add.
     * @param type   Type of money to add.
     */
    public void addMoney(int amount, MoneyType type) {
        if (amount < 1) {
            return;
        }

        int maxAmount = type.getMaxAmount();
        int newAmount = amounts.get(type) + amount;
        if (maxAmount != 0 && newAmount >= maxAmount) {
            addMoney(newAmount / maxAmount, type.getSuperiorMoneyType());

            newAmount = newAmount % maxAmount;
        }

        amounts.put(type, newAmount);
    }

    /**
     * Remove given amount of money.
     *
     * @param amount Amount of money to remove.
     * @param type   Type of money to remove.
     */
    public void removeMoney(int amount, MoneyType type) {
        if (amount < 1) {
            return;
        }

        int maxAmount = type.getMaxAmount();
        int newAmount = amounts.get(type) - amount;
        if (maxAmount != 0 && newAmount < 0) {
            removeMoney((-newAmount / maxAmount) + 1, type.getSuperiorMoneyType());

            newAmount = newAmount + ((-newAmount / maxAmount) + 1) * type.getMaxAmount();
        }

        if (newAmount < 0) {
            throw new IllegalArgumentException("Not enough money !");
        }

        amounts.put(type, newAmount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Money [");
        Set<MoneyType> keys = amounts.keySet();
        for (MoneyType type : keys) {
            sb.append(type.toString());
            sb.append("=");
            sb.append(amounts.get(type));
            sb.append(",");
        }

        return super.toString();
    }
}
