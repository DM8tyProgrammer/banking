package vdx.banking.user.account;

import vdx.banking.user.User;

public class InsufficientFund extends RuntimeException {

    public InsufficientFund(User user) {
        super("Insufficient Balance in account of " + user);
    }
}
