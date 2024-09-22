package web.project.track.service;

import java.util.List;
import web.project.track.entity.Student;

public interface StudentService {

    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(int id);
    List<Student> getAllStudents();
    Student getStudentById(int id);
}