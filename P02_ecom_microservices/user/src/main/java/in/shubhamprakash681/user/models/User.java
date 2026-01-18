package in.shubhamprakash681.user.models;

import in.shubhamprakash681.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Scope("prototype")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    private UserRole role;

    // Note: CascadeType.ALL will propagate all operations (PERSIST, MERGE, REMOVE,
    // REFRESH, DETACH) from User to Address
    // This means when a User is saved, updated, or deleted, the associated Address
    // will also be saved, updated, or deleted accordingly.
    // The orphanRemoval = true setting ensures that if the Address is no longer
    // referenced by the User, it will be removed from the database.
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @PrePersist
    // assigning default value
    private void setDefaultRole() {
        if (role == null) {
            role = UserRole.CUSTOMER;
        }
    }
}