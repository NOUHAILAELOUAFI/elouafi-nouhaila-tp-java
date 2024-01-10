package sge.entities;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private static final List<Student> instances = new ArrayList<>();
    private String firstName;
    private String lastName;
    private String email;
    private int apogee;
    private Major major;

    public Student(){
        Student.instances.add(this);
    }

    public Student(String firstName, String lastName, String email, int apogee, Major major){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.apogee = apogee;
        this.major = major;

        //static
        Student.instances.add(this);
    }

    public static List<Student> getInstances(){
        return Student.instances;
    }

    public String getFirstName() {
        return firstName;
    }

    public Student setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Student setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Student setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getApogee() {
        return apogee;
    }

    public Student setApogee(int apogee) {
        this.apogee = apogee;
        return this;
    }

    public Major getMajor() {
        return major;
    }

    public Student setMajor(Major major) {
        this.major = major;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s - %s, %s - %d %s.", this.firstName,
                this.lastName, this.email,
                this.apogee, this.major == null ? "no major" :this.major.getTitle());
    }
}
