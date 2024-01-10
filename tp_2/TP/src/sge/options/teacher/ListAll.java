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

        for(Teacher teacher: teachers){
            System.out.println("• "+teacher.toString());
        }
        new Teachers().Exec();
    }
}
