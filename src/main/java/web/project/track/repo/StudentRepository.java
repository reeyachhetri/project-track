package web.project.track.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.project.track.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}