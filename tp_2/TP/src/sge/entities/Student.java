package sge.entities;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private static long cursor = 1;
    private long id;
    private long majorId;
    private String firstName;
    private String lastName;
    private String email;
    private int apogee;

    public Student(){
        this.id = cursor;
        Student.cursor++;
    }

    public Student(String firstName, String lastName, String email, int apogee, long majorId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.apogee = apogee;
        this.majorId = majorId;

        this.id = cursor;
        Student.cursor++;
    }

    public long getId(){
        return this.id;
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

    public long getMajor() {
        return this.majorId;
    }

    public Student setMajor(long majorId) {
        this.majorId = majorId;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s ~ %s ~ %s ~ %d", this.firstName,
                this.lastName, this.email,
                this.apogee);
    }
}
