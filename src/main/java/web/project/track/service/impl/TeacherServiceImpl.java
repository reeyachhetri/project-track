package web.project.track.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.project.track.entity.Teacher;
import web.project.track.repo.TeacherRepository; // Ensure this repository is created
import web.project.track.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository; // Inject the repository

    @Override
    public void registerTeacher(Teacher teacher) {
        teacherRepository.save(teacher); // Save the teacher in the database
    }

    @Override
    public Teacher findByUsername(String username) {
        return teacherRepository.findByUsername(username); // Fetch user by username
    }
}
