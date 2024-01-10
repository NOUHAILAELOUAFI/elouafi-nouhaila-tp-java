package sge.services;

import sge.Helpers;
import sge.Utils;
import sge.data.DataContext;
import sge.data.MySQLDataContext;
import sge.entities.Department;
import sge.entities.Teacher;
import sge.interfaces.CrudService;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TeacherService implements CrudService<Teacher> {
    private static TeacherService instance;

    private TeacherService(){}

    public static TeacherService getInstance(){
        if(instance == null) instance = new TeacherService();
        return instance;
    }



    @Override
    public Teacher Get(long id) {
        return MySQLDataContext.getInstance().getTeachers().stream()
                .filter(teacher -> teacher.getId() == id )
                .findFirst()
                .orElse(null);
    }


    public boolean Create(String firstName, String lastName, String grade, String email) throws IllegalArgumentException{
        Pattern gradePattern = Pattern.compile("^[a-z]{1,3}$", Pattern.CASE_INSENSITIVE);



        if(!Helpers.validateEmail(email)){
            throw new IllegalArgumentException("Invalid email format: ");
        }

        Teacher teacher = new Teacher(firstName, lastName, email, grade);
        return MySQLDataContext.getInstance().addTeacher(teacher);
    }
    @Override
    public List<Teacher> GetAll() {
        return MySQLDataContext.getInstance().getTeachers();
    }

    @Override
    public boolean Remove(long id) {

     /*  // must cascade,
        Department department = DataContext.getDepartments().stream().filter(d-> Objects.equals(d.getManagerId(), teacher.getId())).findFirst().orElse(null);
        if(department != null){
            if(!DataContext.removeDepartment(department)){
                System.out.println("Teacher remove Cascade failed");
                return false;
            }
        }*/
       return MySQLDataContext.getInstance().removeTeacher(id);
    }


}
