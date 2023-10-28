package dev.arhimedes.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegistrationRequest {
    private String name;
    private String email;
    private String password;
}
