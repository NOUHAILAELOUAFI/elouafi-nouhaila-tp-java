package sge.options.department;

import sge.entities.Department;
import sge.entities.Teacher;
import sge.interfaces.MenuOption;
import sge.menus.Departments;
import sge.services.DepartmentService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Create implements MenuOption {
    private static final Scanner _scanner = new Scanner(System.in);
    private static final DepartmentService _service = DepartmentService.getInstance();


    @Override
    public void Exec() {
        String name;
        long manager_id;

        System.out.print("Enter Department name: ");
        name = _scanner.nextLine();

        do {
            try {
                System.out.print("Enter teacher ID: ");
                manager_id = _scanner.nextLong();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                _scanner.nextLine();
            }
        }while (true);

        if(!_service.Create(name, manager_id)){
            System.out.println("\u001B[31mFailed to create department\u001B[0m");
        }

        _scanner.nextLine();
        new Departments().Exec();
    }
}
