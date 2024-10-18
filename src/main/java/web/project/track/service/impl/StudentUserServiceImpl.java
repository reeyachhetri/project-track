package web.project.track.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.project.track.entity.Student;
import web.project.track.repo.StudentUserRepository;
import web.project.track.service.StudentUserService;

@Service
public class StudentUserServiceImpl implements StudentUserService {

    @Autowired
    private StudentUserRepository studentUserRepository;

    @Override
    public Student authenticate(String username, String password) {
        Student student = studentUserRepository.findByUsername(username);
        if (student != null && student.getPassword().equals(password)) { // Check password
            return student;
        }
        return null; // Return null if authentication fails
    }
}
