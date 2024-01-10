package sge.entities;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private static final List<Teacher> instances  =new ArrayList<>();
    private String firstName;
    private String lastName;
    private String email;
    private String grade;
    private Department department;

    public Teacher(){
        Teacher.instances.add(this);
    }

    public Teacher(String firstName, String lastName, String email, String grade, Department department){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.grade = grade;
        this.department = department;

        // assign teacher in department instance if department is not null and has no manager
        if(department != null && department.getManager() != null   ){
            department.setManager(this);
        }

        //static
        Teacher.instances.add(this);
    }

    public static List<Teacher> getInstances(){
        return Teacher.instances;
    }

    public String getFirstName() {
        return firstName;
    }

    public Teacher setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Teacher setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Teacher setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getGrade() {
        return grade;
    }

    public Teacher setGrade(String grade) {
        this.grade = grade;
        return this;
    }

    public Department getDepartment() {
        return department;
    }

    public Teacher setDepartment(Department department) {
        this.department = department;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s - %s, %s - %s %s.", this.firstName,
                this.lastName, this.email,
                this.grade, this.department == null ? "no Department" :this.department.getTitle());
    }
}
