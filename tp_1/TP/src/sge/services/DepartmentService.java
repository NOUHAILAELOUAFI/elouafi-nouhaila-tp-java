package sge.services;

import sge.entities.Department;
import sge.entities.Teacher;
import sge.interfaces.CrudService;

import java.util.List;

public class DepartmentService implements CrudService<Department> {
    private static DepartmentService instance;
    private DepartmentService(){}

    public static DepartmentService getInstance() {
        if(instance == null) instance = new DepartmentService();
        return instance;
    }


    @Override
    public List<Department> GetAll() {
        return Department.getInstances();
    }

    @Override
    public Department Get(int index) {
        try{
            return Department.getInstances().get(index);
        }catch (IndexOutOfBoundsException exception){
            return  null;
        }
    }

    public Department Create(String title, Teacher manager) {
        return new Department(title, manager);
    }

    @Override
    public boolean Remove(int index) {
        try{
            Department department = Department.getInstances().get(index);
            department.getManager().setDepartment(null);
            Department.getInstances().remove(department);
            return  true;
        }catch (IndexOutOfBoundsException | UnsupportedOperationException exception){
            return false;
        }
    }

    @Override
    public boolean Remove(Department object) {
       try{
           object.getManager().setDepartment(null);
           Department.getInstances().remove(object);
           return  true;
       }catch (Exception exception){
           return false;
       }
    }
}
