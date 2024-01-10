package sge.services;

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
        return Module.getInstances();
    }

    @Override
    public Module Get(int index) {
        try{
            return Module.getInstances().get(index);
        }catch (IndexOutOfBoundsException | UnsupportedOperationException exception){
            return null;
        }
    }

    public Module Create(String title, Teacher teacher, Major major){
        return new Module(title, major, teacher);
    }

    @Override
    public boolean Remove(int index) {
        try {
            Module.getInstances().remove(index);
            return true;
        } catch (IndexOutOfBoundsException | UnsupportedOperationException exception) {
            return false;
        }
    }

    @Override
    public boolean Remove(Module object) {
        try{
            Module.getInstances().remove(object);
            return true;
        }catch (Exception exception){
            return false;
        }
    }
}
