package web.project.track.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import web.project.track.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    // You can add custom query methods if needed
}
