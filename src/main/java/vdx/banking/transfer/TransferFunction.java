package vdx.banking.transfer;

import vdx.banking.user.User;

public interface TransferFunction {
    void transfer(User sender, User receiver, double amount);
}
