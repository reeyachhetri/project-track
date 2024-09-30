package web.project.track.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.project.track.entity.Teacher;
import web.project.track.repo.TeacherRepository;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void hashAndStorePassword() {
        String rawPassword = "presidentialteacher";
        String hashedPassword = passwordEncoder.encode(rawPassword);

        Teacher teacher = new Teacher();
        teacher.setUsername("teacher");
        teacher.setPassword(hashedPassword);
        // Set any other required fields for the Teacher entity

        teacherRepository.save(teacher); // Save the teacher with the hashed password
    }
}
