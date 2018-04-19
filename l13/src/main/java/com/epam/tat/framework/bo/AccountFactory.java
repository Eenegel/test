package com.epam.tat.framework.bo;

import com.epam.tat.framework.utils.RandomUtil;

public class AccountFactory {

    public static Account getExistentAccount() {
        return new Account("juicy_j16@mail.ru", "qazxsw123");
    }

    public static Account getInvalidAccount() {
        return new Account(RandomUtil.getRandomLogin(), RandomUtil.getRandomPassword());
    }

    public static Account getAccountWithoutPassword() {
        return new Account(RandomUtil.getRandomLogin(), "");
    }

    public static Account getAccountWithoutLogin() {
        return new Account("", RandomUtil.getRandomPassword());
    }
}
