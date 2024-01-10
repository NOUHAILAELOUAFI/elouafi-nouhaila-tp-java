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
        int manager_id;

        System.out.print("Enter Department name: ");
        name = _scanner.nextLine();

        do {
            try {
                System.out.print("Enter teacher ID: ");
                manager_id = _scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                _scanner.nextLine();
            }
        }while (true);


        try{
            Teacher manager = Teacher.getInstances().get(manager_id);
            if(manager.getDepartment() == null) {
                Department department = _service.Create(name, manager);
                System.out.println("Department Created.");
            }else{
                System.out.println("manager already assigned to another department");
            }
        }catch(Exception exception) {
            System.out.println("Invalid Teacher ID.");
        }finally {
            _scanner.nextLine();
            new Departments().Exec();
        }
    }
}
