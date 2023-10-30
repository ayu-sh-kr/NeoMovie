package dev.arhimedes.repository;

import dev.arhimedes.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    @Query(value = "SELECT COUNT(name) FROM roles where name = 'MANAGER'", nativeQuery = true)
    int getManagersCount();
}
