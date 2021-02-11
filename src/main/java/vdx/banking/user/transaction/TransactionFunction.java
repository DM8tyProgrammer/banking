package vdx.banking.user.transaction;

import vdx.banking.user.User;

import java.util.Collection;
import java.util.Date;

public interface TransactionFunction {

    Collection<Transaction> generateMiniStatement(User user);

    Collection<Transaction> generateDetailedStatement(User user, Date start, Date end);
}
