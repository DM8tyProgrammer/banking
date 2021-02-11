package vdx.banking.user;

/**
 * Throws when user is not found in DB
 */
public class UserNotFound extends RuntimeException {

    public UserNotFound(User user) {
        super("user not found "+ user);
    }
}
