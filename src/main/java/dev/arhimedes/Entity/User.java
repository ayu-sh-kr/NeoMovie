package dev.arhimedes.Entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Table(name = "user_details")
@Entity(name = "user_details")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles = new ArrayList<>();
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @Column(name = "account_active")
    private boolean isAccountActive;


    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles.add(new Role());
        this.isAccountActive = false;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }

    public void addRole(String role) {
        if(isValidRole(role)){
            if(!roleExist(role)){
                this.roles.add(new Role(role));
            }
        }
    }

    public boolean roleExist(String role){
        for(Role role1: this.roles){
            if(role1.getName().equals(role)){
                return true;
            }
        }
        return false;
    }

    public boolean isValidRole(String role){
       try {
           Authority.valueOf(role);
           return true;
       }catch (Exception exception){
           System.out.println(exception.getMessage());
           return false;
       }
    }
}
