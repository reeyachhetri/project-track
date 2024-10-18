package web.project.track.service;

import web.project.track.entity.Student;

public interface StudentUserService {
    Student authenticate(String username, String password); // Authentication method
}
