package sge.options.teacher;

import sge.entities.Teacher;
import sge.interfaces.MenuOption;
import sge.menus.Teachers;
import sge.services.TeacherService;

import java.util.List;

public class ListAll implements MenuOption {
    private static final TeacherService  _service = TeacherService.getInstance();

    @Override
    public String GetName() {
        return "List All Teachers";
    }

    @Override
    public void Exec() {
        List<Teacher> teachers = _service.GetAll();
        if(teachers.isEmpty()){
            System.out.println("No Teachers found! try adding one");
            new Teachers().Exec();
            return;
        }

        for(int i = 0; i< teachers.size(); i++){
            System.out.printf("%d• %s\n", i, teachers.get(i).toString());
            if(i == teachers.size() -1){
                System.out.println();
            }
        }
        new Teachers().Exec();
    }
}
