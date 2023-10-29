package dev.arhimedes.dto;

import dev.arhimedes.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminFrontUserDTO implements Serializable {
    private String name;
    private String email;
    private List<Role> roleList;
}
