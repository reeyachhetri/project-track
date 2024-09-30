package web.project.track.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import web.project.track.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findByUsername(String username); // Method to find a teacher by username
}
