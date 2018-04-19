package com.epam.gomel.homework;

import lombok.Getter;
import lombok.Setter;

public class Girl extends AbstractHuman {

    @Setter
    @Getter
    private boolean isPretty;
    @Getter
    @Setter
    private Boy boyFriend;
    @Getter
    private boolean isSlimFriendGotAFewKilos;

    public Girl(boolean isPretty, boolean isSlimFriendGotAFewKilos, Boy boyFriend) {
        this.isPretty = isPretty;
        this.isSlimFriendGotAFewKilos = isSlimFriendGotAFewKilos;
        this.boyFriend = boyFriend;
        //this.boyFriend.setGirlFriend(this); BUG
    }

    public Girl(boolean isPretty, boolean isSlimFriendGotAFewKilos) {
        this(isPretty, isSlimFriendGotAFewKilos, null);
    }

    public Girl(boolean isPretty) {
        this(isPretty, false, null);
    }

    public Girl() {
        this(false, true, null);
    }

    @Override
    public Mood getMood() {
        if (isBoyFriendWillBuyNewShoes()) {
            return Mood.EXCELLENT;
        } else if (isPretty() || isBoyfriendRich()) {
            return Mood.GOOD;
        } else if (isSlimFriendBecameFat()) {
            return Mood.NEUTRAL;
        }
        return Mood.I_HATE_THEM_ALL;
    }

    public void spendBoyFriendMoney(double amountForSpending) {
        if (isBoyfriendRich()) {
            getBoyFriend().spendSomeMoney(amountForSpending);
        }
    }

    public boolean isBoyfriendRich() {
        return getBoyFriend() /*=*/ != null && getBoyFriend().isRich();
    }

    public boolean isBoyFriendWillBuyNewShoes() {
        return isBoyfriendRich() && isPretty();
    }

    public boolean isSlimFriendBecameFat() {
        return isSlimFriendGotAFewKilos() && !isPretty();
    }
}
