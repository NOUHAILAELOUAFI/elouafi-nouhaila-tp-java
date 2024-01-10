package sge.options.student;

import sge.entities.Department;
import sge.entities.Student;
import sge.interfaces.MenuOption;
import sge.menus.Departments;
import sge.menus.Students;
import sge.services.StudentService;

import java.util.List;

public class ListAll implements MenuOption {
    private static final StudentService _service = StudentService.getInstance();

    @Override
    public String GetName() {
        return "List All Students";
    }

    @Override
    public void Exec() {
        List<Student> students = _service.GetAll();
        if (students.isEmpty()) {
            System.out.println("No departments found! try adding one");
            new Departments().Exec();
            return;
        }

        for (int i = 0; i < students.size(); i++) {
            System.out.printf("%d• %s\n", i, students.get(i).toString());
            if (i == students.size() - 1) {
                System.out.println();
            }
        }
        new Students().Exec();
    }
}
