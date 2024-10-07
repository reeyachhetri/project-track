package web.project.track;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import web.project.track.entity.Student;
import web.project.track.repo.StudentRepository;
import web.project.track.service.impl.StudentServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentServiceImplIntegrationTest {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void setUp() {
        studentRepository.deleteAll();  // Clean up before each test
    }

    // For adding
    @Test
    public void testAddStudent() {
        Student student = new Student();
        student.setName("John Doe");
        student.setGroup("Group A");
        student.setRole("Leader");
        studentService.addStudent(student);

        List<Student> students = studentService.getAllStudents();
        assertEquals(1, students.size());
        assertEquals("John Doe", students.get(0).getName());
    }

    // Retrieving by ID
    @Test
    public void testGetStudentById() {
        Student student = new Student();
        student.setName("John Doe");
        student.setGroup("Group A");
        student.setRole("Leader");
        studentService.addStudent(student);

        Student foundStudent = studentService.getStudentById(student.getId());
        assertNotNull(foundStudent);
        assertEquals("John Doe", foundStudent.getName());
    }

    // Updating
    @Test
    public void testUpdateStudent() {
        Student student = new Student();
        student.setName("John Doe");
        student.setGroup("Group A");
        student.setRole("Leader");
        studentService.addStudent(student);

        student.setName("Jane Doe");
        studentService.updateStudent(student);

        Student updatedStudent = studentService.getStudentById(student.getId());
        assertEquals("Jane Doe", updatedStudent.getName());
    }

    // Deleting
    @Test
    public void testDeleteStudent() {
        Student student = new Student();
        student.setName("John Doe");
        student.setGroup("Group A");
        student.setRole("Leader");
        studentService.addStudent(student);

        studentService.deleteStudent(student.getId());
        Student foundStudent = studentService.getStudentById(student.getId());
        assertNull(foundStudent);
    }

    // Updating non-existent (fail)
    @Test
    public void testUpdateNonExistentStudent() {
        Student student = new Student();
        student.setId(999);  // Non-existent ID
        student.setName("Non-existent Student");
        assertThrows(RuntimeException.class, () -> studentService.updateStudent(student));
    }

    // Deleting non-existent (fail)
    @Test
    public void testDeleteNonExistentStudent() {
        int nonExistentId = 999;
        assertThrows(RuntimeException.class, () -> studentService.deleteStudent(nonExistentId));
    }
}
