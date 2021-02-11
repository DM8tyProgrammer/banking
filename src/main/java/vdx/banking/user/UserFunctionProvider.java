package vdx.banking.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import vdx.banking.user.account.Account;
import vdx.banking.user.account.AccountRepository;
import vdx.banking.user.account.InsufficientFund;
import vdx.banking.user.transaction.Transaction;
import vdx.banking.user.transaction.TransactionRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Component
@Primary
class UserFunctionProvider implements UserAccountFunction {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public double getBalance(User user) {
        return userRepository.find(user)
                .map(User::getAccount)
                .map(Account::getBalance)
                .orElseThrow(() -> new UserNotFound(user));

    }

    @Override
    @Transactional
    public double updateBalance(User user, double byAmount) {
        Optional<User> mayBeUser = userRepository.find(user);

        // check existence of user
        if(! /*not */ mayBeUser.isPresent()) {
            throw new UserNotFound(user);
        }

        User persistedUser = mayBeUser.get();
        Account account = persistedUser.getAccount();

        // update the balance
        double updatedBalance = account.getBalance() + byAmount;

        // Report if there is no fund
        if (updatedBalance < 0) {
            throw new InsufficientFund(user);
        }

        account.setBalance(updatedBalance);

        // generate transaction
        Transaction transaction = new Transaction(byAmount, new Date(), persistedUser);
        transactionRepository.save(transaction);

        // attach back persisted root
        userRepository.save(persistedUser);

        return updatedBalance;
    }
}
