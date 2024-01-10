package sge.options.module;

import sge.entities.Module;
import sge.interfaces.MenuOption;
import sge.menus.Majors;
import sge.menus.Teachers;
import sge.services.ModuleService;

import java.util.List;

public class ListAll implements MenuOption {
    private static final ModuleService _service = ModuleService.getInstance();

    @Override
    public String GetName() {
        return "List All Modules";
    }

    @Override
    public void Exec() {
        List<Module> modules = _service.GetAll();

        if(modules.isEmpty()){
            System.out.println("No Teachers found! try adding one");
            new Teachers().Exec();
            return;
        }

        for(int i = 0; i< modules.size(); i++){
            System.out.printf("%d• %s\n", i, modules.get(i).getTitle());
            if(i == modules.size() -1){
                System.out.println();
            }
        }
        new Majors().Exec();
    }
}
