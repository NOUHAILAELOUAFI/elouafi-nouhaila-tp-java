package sge.services;

import sge.entities.Major;
import sge.entities.Student;
import sge.interfaces.CrudService;
import sge.menus.Students;

import java.util.List;

public class StudentService implements CrudService<Student> {
    private static StudentService instance;

    private StudentService(){}

    public static StudentService getInstance(){
        if(StudentService.instance == null) StudentService.instance = new StudentService();
        return StudentService.instance;
    }

    @Override
    public List<Student> GetAll() {
        return Student.getInstances();
    }

    @Override
    public Student Get(int index) {
        try{
            return Student.getInstances().get(index);
        }catch (IndexOutOfBoundsException exception){
            return null;
        }
    }

    public Student Create(String firstName, String lastName, String email, int apogee, Major major){
        return new Student().setFirstName(firstName).setLastName(lastName)
                .setEmail(email).setApogee(apogee).setMajor(major);
    }

    @Override
    public boolean Remove(int index) {
        try{
            Student.getInstances().remove(index);
            return true;
        }catch (IndexOutOfBoundsException | UnsupportedOperationException exception){
            return false;
        }
    }

    @Override
    public boolean Remove(Student object) {
        try{
            Student.getInstances().remove(object);
            return true;
        }catch (IndexOutOfBoundsException | UnsupportedOperationException exception){
            return false;
        }    }
}
