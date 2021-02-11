package vdx.banking.user;

import vdx.banking.user.account.Account;
import vdx.banking.user.transaction.Transaction;

import javax.persistence.*;
import java.util.Collection;

/**
 * User
 *  - Account
 *  - Transaction
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @OneToMany(mappedBy="user")
    private Collection<Transaction> transactions;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String firstName, String lastName, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = account;
    }


    protected User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Account getAccount() {
        return account;
    }

    public Collection<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name ='" + firstName + " " + lastName + "'}";
    }
}
