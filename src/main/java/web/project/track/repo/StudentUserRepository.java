package web.project.track.repo;

import web.project.track.entity.StudentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentUserRepository extends JpaRepository<StudentUser, Integer> {
    Optional<StudentUser> findByUsername(String username);
}

