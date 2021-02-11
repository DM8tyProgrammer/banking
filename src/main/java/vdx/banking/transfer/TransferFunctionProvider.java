package vdx.banking.transfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import vdx.banking.user.User;
import vdx.banking.user.UserAccountFunction;

import javax.transaction.Transactional;

@Component
@Primary
public class TransferFunctionProvider implements TransferFunction {

    @Autowired
    private UserAccountFunction userAccountFunction;

    @Transactional
    public void transfer(User sender, User receiver, double amount) {
        // both statement is transaction, there is insufficient balance transaction would be reverse

        // update balance handles all the cases
        // 1. if user not present -> throws error
        // 2. if user balance is not sufficient for transfer -> throws error
        userAccountFunction.updateBalance(sender, -1 * amount);
        userAccountFunction.updateBalance(receiver, amount);
    }
}
