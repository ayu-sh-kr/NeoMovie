package dev.arhimedes.dto;

import dev.arhimedes.Entity.Role;

import java.util.List;

public interface AdminUserDTO {
    String getName();
    String getEmail();
    List<Role> getRoles();
}
