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
        return null;
    }

    @Override
    public Student Get(long index) {
        return null;
    }

    @Override
    public boolean Remove(long index) {
        return false;
    }


}
