package vdx.banking;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vdx.banking.transfer.TransferFunction;
import vdx.banking.user.User;
import vdx.banking.user.UserAccountFunction;
import vdx.banking.user.transaction.Transaction;
import vdx.banking.user.transaction.TransactionFunction;

import java.util.Collection;
import java.util.Date;

/**
 * Facade of Banking
 */
@Component
public class BankingFunction implements BankingInterface {

    @Autowired
    private UserAccountFunction userAccountFunction;

    @Autowired
    private TransactionFunction transactionFunction;

    @Autowired
    private TransferFunction transferFunction;

    @Override
    public double getBalance(User user) {
        return userAccountFunction.getBalance(user);
    }

    @Override
    public double updateBalance(User user, double balance) {
        return userAccountFunction.updateBalance(user, balance);
    }


    @Override
    public Collection<Transaction> generateMiniStatement(User user) {
        return transactionFunction.generateMiniStatement(user);
    }

    @Override
    public Collection<Transaction> generateDetailedStatement(User user, Date start, Date end) {
        return transactionFunction.generateDetailedStatement(user, start, end);
    }

    @Override
    public void transfer(User sender, User receiver, double amount) {
        transferFunction.transfer(sender, receiver, amount);
    }
}
