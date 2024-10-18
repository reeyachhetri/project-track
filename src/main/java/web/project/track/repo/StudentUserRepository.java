package web.project.track.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import web.project.track.entity.Student; // Assuming you still need this for some operations

public interface StudentUserRepository extends JpaRepository<Student, Integer> {
    Student findByUsername(String username); // Method to find by username
}
