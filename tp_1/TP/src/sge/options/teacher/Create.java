package sge.options.teacher;

import sge.entities.Teacher;
import sge.interfaces.MenuOption;
import sge.menus.Teachers;
import sge.services.TeacherService;

import java.util.Scanner;

public class Create implements MenuOption {
    private static final Scanner _scanner = new Scanner(System.in);
    private static final TeacherService _service = TeacherService.getInstance();

    @Override
    public void Exec() {
        String firstName, lastName, email, grade;
        int department_id;

        System.out.print("Enter First name: ");
        firstName = _scanner.nextLine();
        System.out.print("Enter Last name: ");
        lastName = _scanner.nextLine();
        System.out.print("Enter email: ");
        email = _scanner.nextLine();
        System.out.print("Enter grade:  ");
        grade = _scanner.nextLine();

        Teacher teacher  = _service.Create(firstName, lastName, grade, email, null);
        _scanner.nextLine();
        new Teachers().Exec();
    }
}
