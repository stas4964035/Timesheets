package k44.timesheets.repository;

import k44.timesheets.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<Role, Long> {


    List<Role> findByUserId(@Param("user_id") Long userId);
}
