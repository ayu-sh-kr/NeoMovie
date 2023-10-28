package dev.arhimedes.Entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;


@Table(name = "user_details")
@Entity(name = "user_details")
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private HashSet<Authority> authorityCollection = new HashSet<>();
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;


    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.authorityCollection.add(Authority.USER);
    }

}
