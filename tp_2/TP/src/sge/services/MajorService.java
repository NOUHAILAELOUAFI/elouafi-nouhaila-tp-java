package sge.services;

import sge.data.DataContext;
import sge.entities.Department;
import sge.entities.Major;
import sge.entities.Teacher;
import sge.interfaces.CrudService;

import java.util.List;

public class MajorService implements CrudService<Major> {

    private static MajorService instance;
    private MajorService(){}

    public static MajorService getInstance() {
        if(instance == null) instance = new MajorService();
        return instance;
    }

    @Override
    public List<Major> GetAll() {
        return DataContext.getMajors();
    }

    @Override
    public Major Get(long id) {
        return DataContext.getMajors().stream().filter(major -> major.getId() == id).findFirst().orElse(null);
    }

    public boolean Create(String title,long teacherId, long departmentId){
        if(TeacherService.getInstance().Get(teacherId) == null){
            System.out.println("Invalid Id, Teacher does not or no longer exists");
            return  false;
        }
        if(DepartmentService.getInstance().Get(departmentId) == null){
            System.out.println("Invalid Id, Department does not or no longer exists");
        }
        Major major = new Major(title,teacherId, departmentId);
        return DataContext.addMajor(major);
    }

    @Override
    public boolean Remove(long id) {
        Major major = this.Get(id);
        if(major == null){
            System.out.println("Invalid Id, major does not exists");
            return false;
        }
        return DataContext.removeMajor(major);
    }


}
