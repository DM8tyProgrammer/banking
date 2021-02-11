package vdx.banking;

import org.springframework.stereotype.Component;
import vdx.banking.transfer.TransferFunction;
import vdx.banking.user.transaction.TransactionFunction;
import vdx.banking.user.UserAccountFunction;


public interface BankingInterface extends UserAccountFunction, TransactionFunction, TransferFunction {
}
