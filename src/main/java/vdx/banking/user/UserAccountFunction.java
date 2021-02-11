package vdx.banking.user;

public interface UserAccountFunction {

    /**
     *
     * @param user
     * @return
     */
    double getBalance(User user);

    /**
     *
     * @param user
     * @param balance
     * @return
     */
    double updateBalance(User user, double balance);
}
