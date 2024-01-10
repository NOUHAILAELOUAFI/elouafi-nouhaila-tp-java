package sge.services;

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
        return Major.getInstances();
    }

    public Major Create(String title, Department department, Teacher responsible){
        return new Major().setResponsible(responsible).setDepartment(department).setTitle(title);
    }

    @Override
    public Major Get(int index) {
        try{
            return Major.getInstances().get(index);
        }catch(IndexOutOfBoundsException exception) {
            return null;
        }
    }

    @Override
    public boolean Remove(int index) {
        return false;
    }

    @Override
    public boolean Remove(Major object) {
        return false;
    }
}
