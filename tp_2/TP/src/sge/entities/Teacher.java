package sge.entities;

import sge.data.DataContext;


public class Teacher {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String grade;


    public Teacher() {}
    public Teacher(String firstName, String lastName, String email, String grade){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.grade = grade;
    }


    public Long getId() {
        return this.id;
    }

    public Teacher setId(Long id){
        this.id = id;
        return this;
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

    @Override
    public String toString() {
        return String.format("%d ~ %s ~ %s ~ %s ~ %s",
                this.id,
                this.firstName,
                this.lastName, this.email,
                this.grade
        );
    }

}
