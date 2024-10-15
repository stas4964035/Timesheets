package k44.timesheets.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "users_roles")
@Data
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="user_id")
    private Long userId;

    @Column(name = "role_name")
    private String roleName;


}
