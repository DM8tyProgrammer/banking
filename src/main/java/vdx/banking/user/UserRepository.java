package vdx.banking.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);


    default Optional<User> find(User user) {
        // note: user should be searched by id ideally;
        return findByFirstNameAndLastName(user.getFirstName(), user.getLastName());
    }
}
