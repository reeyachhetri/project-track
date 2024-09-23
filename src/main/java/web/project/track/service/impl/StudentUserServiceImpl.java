package web.project.track.service.impl;

import web.project.track.entity.StudentUser;
import web.project.track.repo.StudentUserRepository;
import web.project.track.service.StudentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentUserServiceImpl implements StudentUserService {

    @Autowired
    private StudentUserRepository studentUserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public StudentUser registerStudent(String username, String password, String name) {
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("Encoded Password: " + encodedPassword); // Debugging
        StudentUser studentUser = new StudentUser(username, encodedPassword, name);
        return studentUserRepository.save(studentUser);
    }

    @Override
    public StudentUser findByUsername(String username) {
        StudentUser studentUser = studentUserRepository.findByUsername(username).orElse(null);
        if (studentUser != null) {
            System.out.println("Retrieved Username: " + studentUser.getUsername());
            System.out.println("Stored Password: " + studentUser.getPassword()); // Debugging
        }
        return studentUser;
    }

}
