package sge.services;

import sge.Helpers;
import sge.data.DataContext;
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
        return DataContext.getDepartments();
    }

    @Override
    public Department Get(long id) {
        return DataContext.getDepartments().stream()
                .filter(department -> department.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean Create(String title, long managerId) {
        if(!Helpers.validateIdentityInfo(title)){
            System.out.println("Invalid title, min(3)");
            return false;
        }
        if(TeacherService.getInstance().Get(managerId) == null){
            System.out.println("Invalid Id, Teacher does not or no longer exists");
            return false;
        }
        Department department = new Department(title, managerId);
        return DataContext.addDepartment(department);
    }

    @Override
    public boolean Remove(long id) {
        Department department = this.Get(id);
        if(department == null){
            System.out.println("Invalid Id, department does not exists");
            return false;
        }
        return DataContext.removeDepartment(department);
    }
}
