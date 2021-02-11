package vdx.banking.user.transaction;


import vdx.banking.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private double amount;

    @Temporal(TemporalType.DATE)
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Transaction(double amount, Date timestamp, User user) {
        this.amount = amount;
        this.timestamp = timestamp;
        this.user = user;
    }

    protected Transaction() {
    }

    public double getAmount() {
        return amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", user=" + user +
                '}';
    }
}
