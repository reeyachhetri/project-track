package web.project.track.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.project.track.entity.StudentUser;
import web.project.track.repo.StudentUserRepository; // Make sure you create this repository
import web.project.track.service.StudentUserService;

@Service
public class StudentUserServiceImpl implements StudentUserService {

    @Autowired
    private StudentUserRepository studentUserRepository; // Inject the repository

    @Override
    public void registerStudent(String username, String password, String name) {
        StudentUser studentUser = new StudentUser();
        studentUser.setUsername(username);
        studentUser.setPassword(password); // Password is already encoded in controller
        studentUser.setName(name);
        studentUserRepository.save(studentUser); // Save to the database
    }

    @Override
    public StudentUser findByUsername(String username) {
        return studentUserRepository.findByUsername(username); // Fetch user by username
    }
}
