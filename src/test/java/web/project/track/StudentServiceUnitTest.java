package web.project.track;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import web.project.track.entity.Student;
import web.project.track.repo.StudentRepository;
import web.project.track.service.impl.StudentServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceUnitTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student();
        student.setId(1);
        student.setName("John Doe");
        student.setUsername("johndoe");
        student.setPassword("password123");
        student.setGroups(1);
        student.setRole("Leader");
    }

    // 1. Test for Adding a Student
    @Test
    public void testAddStudent() {
        studentService.addStudent(student); // Call the method, no return value to check
        verify(studentRepository, times(1)).save(student); // Verify that save was called
    }

    // 2. Test for Fetching All Students
    @Test
    public void testGetAllStudents() {
        List<Student> students = Arrays.asList(student);
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = studentService.getAllStudents();
        assertEquals(1, result.size());
        assertEquals(student, result.get(0)); // Ensure the returned student is the same
        verify(studentRepository, times(1)).findAll(); // Verify the repository method was called
    }

    // 3. Test for Getting a Student by ID (Found)
    @Test
    public void testGetStudentByIdFound() {
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));

        Student result = studentService.getStudentById(student.getId());
        assertNotNull(result);
        assertEquals(student.getId(), result.getId());
        verify(studentRepository, times(1)).findById(student.getId());
    }

    // 4. Test for Getting a Student by ID (Not Found)
    @Test
    public void testGetStudentByIdNotFound() {
        when(studentRepository.findById(10)).thenReturn(Optional.empty());

        Student result = studentService.getStudentById(10);
        assertNull(result); // Expecting null since student does not exist
        verify(studentRepository, times(1)).findById(10);
    }

    // 5. Test for Updating a Student
    @Test
    public void testUpdateStudent() {
        studentService.updateStudent(student); // Call the method, no return value to check
        verify(studentRepository, times(1)).save(student); // Verify that save was called
    }

    // 6. Test for Deleting a Student
    @Test
    public void testDeleteStudent() {
        doNothing().when(studentRepository).deleteById(student.getId());

        studentService.deleteStudent(student.getId()); // Call the method
        verify(studentRepository, times(1)).deleteById(student.getId()); // Verify delete was called
    }
}
