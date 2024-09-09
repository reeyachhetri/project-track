package web.project.track.entity;

public class Student {
    private int id;
    private String name;
    private String group;
    private String role;

    public Student() {
    }

    public Student(int id, String name, String group, String role) {
        this.id = id;
        this.name = name;
        this.group = group;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
