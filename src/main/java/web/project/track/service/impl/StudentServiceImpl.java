package web.project.track.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.project.track.entity.Student;
import web.project.track.repo.StudentRepository;
import web.project.track.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void updateStudent(Student student) {
        studentRepository.save(student); // Save will update if it already exists
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }
}
