package dev.arhimedes.repository;

import dev.arhimedes.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    Boolean existsByEmail(String email);

    @Query(value = "SELECT COUNT(name) FROM user_details ", nativeQuery = true)
    int getCount();
}
