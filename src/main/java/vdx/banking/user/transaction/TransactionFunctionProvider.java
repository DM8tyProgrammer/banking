package vdx.banking.user.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import vdx.banking.user.*;
import vdx.banking.user.UserNotFound;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Component
@Primary
class TransactionFunctionProvider implements TransactionFunction {

    @Autowired
    private TransactionRepository transactionRepository;


    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Collection<Transaction> generateMiniStatement(User user) {

        Optional<User> mayBeUser = userRepository.find(user);

        if(! /*not */ mayBeUser.isPresent()) {
            throw new UserNotFound(user);
        }

        User persistedUser = mayBeUser.get();

        // or get user.transaction // filter in memory : might cause out-of-memory

        return transactionRepository.findTop10ByOrderByTimestampDesc(persistedUser, PageRequest.of(0, 10));
    }

    @Override
    public Collection<Transaction> generateDetailedStatement(User user, Date start, Date end) {
        Optional<User> mayBeUser = userRepository.find(user);

        if(! /*not */ mayBeUser.isPresent()) {
            throw new UserNotFound(user);
        }

        return transactionRepository.findWithinTimestamp(user, start, end);
    }
}
