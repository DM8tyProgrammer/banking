package vdx.banking.user.account;

import vdx.banking.user.User;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Double balance;

    @OneToOne(mappedBy = "account")
    private User user;

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    protected Account() {
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
