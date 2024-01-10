package sge.options.department;

import sge.entities.Department;
import sge.interfaces.MenuOption;
import sge.menus.Departments;
import sge.services.DepartmentService;

import java.util.List;

public class ListAll implements MenuOption {
    private static final DepartmentService _service = DepartmentService.getInstance();

    @Override
    public String GetName() {
        return "List All departments.";
    }

    @Override
    public void Exec() {
        List<Department> departments = _service.GetAll();
        if(departments.isEmpty()){
            System.out.println("No departments found! try adding one");
            new Departments().Exec();
            return;
        }

        for(int i = 0; i< departments.size(); i++){
            System.out.printf("%d• %s\n", i, departments.get(i).toString());
            if(i == departments.size() -1){
                System.out.println();
            }
        }
        new Departments().Exec();
    }
}
