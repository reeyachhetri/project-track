package web.project.track.service;

import web.project.track.entity.Teacher;

public interface TeacherService {
    void registerTeacher(Teacher teacher); // Ensure this method is present
    Teacher findByUsername(String username); // Ensure this method is present
}
