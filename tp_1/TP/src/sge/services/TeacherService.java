package sge.services;

import sge.entities.Department;
import sge.entities.Teacher;
import sge.interfaces.CrudService;

import java.util.List;

public class TeacherService implements CrudService<Teacher> {
    private static TeacherService instance;

    private TeacherService(){}

    public static TeacherService getInstance(){
        if(instance == null) instance = new TeacherService();
        return instance;
    }

    @Override
    public List<Teacher> GetAll() {
        return Teacher.getInstances();
    }

    @Override
    public Teacher Get(int index) {
        try{
            return Teacher.getInstances().get(index);
        }catch (IndexOutOfBoundsException exception){
            return  null;
        }
    }


    public Teacher Create(String firstName, String lastName, String grade, String email,Department department){
        return new Teacher()
                .setFirstName(firstName).setLastName(lastName).setEmail(email).setGrade(grade).setDepartment(department);
    }

    @Override
    public boolean Remove(int index) {
        try{
            Teacher teacher =  Teacher.getInstances().get(index);
            return  true;
        }catch (IndexOutOfBoundsException | UnsupportedOperationException exception){
            return false;
        }
    }

    @Override
    public boolean Remove(Teacher object) {
        try{
            Department department = object.getDepartment();
            if(department!= null){
                if(!Department.getInstances().remove(department)){
                    System.out.println("Failed To remove Department associated");
                    return false;
                }
            }
            Teacher.getInstances().remove(object);
            return  true;
        }catch (Exception exception){
            return  false;
        }
    }
}
