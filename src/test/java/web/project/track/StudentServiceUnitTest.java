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
        student.setGroup("Group A");
        student.setRole("Leader");
    }

    // 1. Test for Adding
    @Test
    public void testAddStudent() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        studentService.addStudent(student);
        verify(studentRepository, times(1)).save(student);
    }

    // 2. Test for All Student
    @Test
    public void testGetAllStudents() {
        List<Student> students = Arrays.asList(student);
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = studentService.getAllStudents();
        assertEquals(1, result.size());
        verify(studentRepository, times(1)).findAll();
    }

    // 3. Test for Updating
    @Test
    public void testUpdateStudent() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        studentService.updateStudent(student);
        verify(studentRepository, times(1)).save(student);
    }

    // 4. Test for Deleting
    @Test
    public void testDeleteStudent() {
        doNothing().when(studentRepository).deleteById(student.getId());
        studentService.deleteStudent(student.getId());
        verify(studentRepository, times(1)).deleteById(student.getId());
    }

    // 5. Fail Test for getting a student by ID
    @Test
    public void testGetStudentByIdNotFound() {
        when(studentRepository.findById(anyInt())).thenReturn(Optional.empty());

        Student result = studentService.getStudentById(10);
        assertNull(result);
        verify(studentRepository, times(10)).findById(10);
    }

    // 6. Fail Test
    @Test
    public void testAddStudentWithNullName() {
        Student invalidStudent = new Student();
        invalidStudent.setId(2);
        invalidStudent.setName(null);

        RuntimeException thrown = assertThrows(RuntimeException.class,
                () -> studentService.addStudent(invalidStudent));

        assertEquals("Name cannot be blank", thrown.getMessage());
        verify(studentRepository, never()).save(any(Student.class));
    }
}
