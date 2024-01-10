package sge.services;

import sge.data.DataContext;
import sge.entities.Major;
import sge.entities.Module;
import sge.entities.Teacher;
import sge.interfaces.CrudService;

import java.util.List;

public class ModuleService implements CrudService<Module> {
    private static ModuleService instance;

    private ModuleService(){}

    public static ModuleService getInstance(){
        if(instance == null) instance = new ModuleService();
        return instance;
    }


    @Override
    public List<Module> GetAll() {
        return DataContext.getModules();
    }

    @Override
    public Module Get(long id) {
        return DataContext.getModules().stream()
                .filter(module -> module.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean Create(String title, long teacherId, long majorId){
        if(MajorService.getInstance().Get(majorId) == null){
            System.out.println("Invalid Id, Teacher does not or no longer exists");
            return false;
        }
        if(TeacherService.getInstance().Get(teacherId) == null){
            System.out.println("Invalid Id, Teacher does not or no longer exists");
            return false;
        }

        Module module = new Module(title, majorId, teacherId);
        return DataContext.addModule(module);
    }

    @Override
    public boolean Remove(long id) {
        Module module = this.Get(id);
        if(module == null){
            System.out.println("Invalid Id, major does not exists");
            return false;
        }
        return DataContext.removeModule(module);
    }


}
