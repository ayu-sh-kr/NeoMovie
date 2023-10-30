package dev.arhimedes.repository;

import dev.arhimedes.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    Boolean existsByEmail(String email);

    @Query(value = "SELECT COUNT(name) FROM user_details ", nativeQuery = true)
    int getCount();

    @Query(value = "SELECT COUNT(account_active) FROM user_details WHERE account_active = 1;", nativeQuery = true)
    int getActiveAccountCount();

    @Query(value = "SELECT * FROM user_details WHERE account_active = 1;", nativeQuery = true)
    List<User> getActiveUsers();
}
