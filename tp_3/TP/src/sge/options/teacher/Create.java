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

        System.out.print("Enter First name: ");
        firstName = _scanner.nextLine();
        System.out.print("Enter Last name: ");
        lastName = _scanner.nextLine();
        System.out.print("Enter email: ");
        email = _scanner.nextLine();
        System.out.print("Enter grade:  ");
        grade = _scanner.nextLine();

        if(!_service.Create(firstName, lastName, grade, email)){
            System.out.println("\u001B[31mFailed to create teacher\u001B[0m");

        }
        _scanner.nextLine();
        new Teachers().Exec();
    }
}
