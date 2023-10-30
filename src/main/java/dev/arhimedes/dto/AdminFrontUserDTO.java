package dev.arhimedes.dto;

import dev.arhimedes.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminFrontUserDTO implements Serializable {
    private Long id;
    private String name;
    private String email;
    private List<Role> roleList;
    private LocalDateTime createdOn;
    private boolean isActive;
}
