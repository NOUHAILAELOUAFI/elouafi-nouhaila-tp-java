package sge.options.major;

import sge.entities.Major;
import sge.interfaces.MenuOption;
import sge.menus.Departments;
import sge.menus.Majors;
import sge.services.MajorService;

import java.util.List;

public class ListAll implements MenuOption {
    private static final MajorService _service = MajorService.getInstance();

    @Override
    public String GetName() {
        return "List All Majors.";
    }

    @Override
    public void Exec() {
        List<Major> majors = _service.GetAll();
        if(majors.isEmpty()){
            System.out.println("No departments found! try adding one");
            return;
        }

        for(Major major: majors){
            System.out.printf("• %s\n",major.toString());
        }
        new Majors().Exec();
    }
}
