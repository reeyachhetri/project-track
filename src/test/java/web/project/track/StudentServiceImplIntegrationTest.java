package web.project.track;

import static org.mockito.Mockito.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import web.project.track.entity.Student;
import web.project.track.repo.StudentRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DataJpaTest
@AutoConfigureMockMvc
public class StudentServiceImplIntegrationTest {

    @Autowired
    private StudentRepository studentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private Student student;

    @BeforeEach
    public void setUp() {
        student = new Student();
        student.setId(1); // Set initial ID if needed
        student.setName("John Doe");
        student.setUsername("johndoe");
        student.setPassword("password123");
        student.setGroups(1); // Use setGroups instead of setGroup
        student.setRole("Leader");

        // Save the student to the repository to set up the database state for testing
        studentRepository.save(student);
    }

    @Test
    public void testFindStudentById() {
        Optional<Student> foundStudent = studentRepository.findById(student.getId());
        assertTrue(foundStudent.isPresent());
        assertEquals(student.getId(), foundStudent.get().getId());
    }

    @Test
    public void testDeleteStudent() {
        studentRepository.deleteById(student.getId());
        Optional<Student> deletedStudent = studentRepository.findById(student.getId());
        assertFalse(deletedStudent.isPresent());
    }

    @Test
    public void testUpdateStudent() {
        student.setName("Jane Doe"); // Update the name
        studentRepository.save(student); // Save the updated student

        Optional<Student> updatedStudent = studentRepository.findById(student.getId());
        assertTrue(updatedStudent.isPresent());
        assertEquals("Jane Doe", updatedStudent.get().getName());
    }
}
