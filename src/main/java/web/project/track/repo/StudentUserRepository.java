package web.project.track.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import web.project.track.entity.StudentUser;

public interface StudentUserRepository extends JpaRepository<StudentUser, Integer> {
    StudentUser findByUsername(String username); // Method to find user by username
}
