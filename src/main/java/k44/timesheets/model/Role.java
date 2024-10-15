package k44.timesheets.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name="roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="role_name")
    private String name;


}
