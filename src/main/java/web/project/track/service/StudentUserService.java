package web.project.track.service;

import web.project.track.entity.StudentUser;

public interface StudentUserService {
    void registerStudent(String username, String password, String name);
    StudentUser findByUsername(String username);
}
