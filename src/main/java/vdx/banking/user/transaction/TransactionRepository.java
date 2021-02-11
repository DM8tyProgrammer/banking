package vdx.banking.user.transaction;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import vdx.banking.user.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    @Query("SELECT t from Transaction t where user = ?1 ORDER BY timestamp DESC")
    List<Transaction> findTop10ByOrderByTimestampDesc(User user, Pageable pageable);


    @Query("SELECT t from Transaction t where user = ?1 and timestamp >= ?2 and timestamp < ?3  ORDER BY timestamp ASC")
    List<Transaction> findWithinTimestamp(User user, Date date1, Date date2);
}
