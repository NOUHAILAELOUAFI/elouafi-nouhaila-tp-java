package sge.options.major;

import sge.entities.Department;
import sge.entities.Major;
import sge.entities.Teacher;
import sge.interfaces.MenuOption;
import sge.menus.Majors;
import sge.services.DepartmentService;
import sge.services.MajorService;
import sge.services.ModuleService;
import sge.services.TeacherService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Create implements MenuOption {
    private static final Scanner _scanner = new Scanner(System.in);
    private static final MajorService _service = MajorService.getInstance();
    private static final TeacherService _teacherService = TeacherService.getInstance();
    private static final DepartmentService _departmentService = DepartmentService.getInstance();

    @Override
    public void Exec() {

        int manager_id;

        System.out.print("Enter Major name: ");
        String title = _scanner.nextLine();

        do {
            try {
                System.out.print("Enter teacher ID: ");
                manager_id = _scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }finally {
                _scanner.nextLine();
            }
        }while (true);



        Teacher manager = _teacherService.Get(manager_id);
        if(manager == null){
            System.out.println("Invalid Teacher ID");
            return;
        }

        long departmentId;
        do {
            try {
                System.out.print("Enter department ID: ");
                departmentId = _scanner.nextLong();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                _scanner.nextLine();
            }
        }while (true);

        Department department = _departmentService.Get(departmentId);


        if(department == null){
            System.out.println("Invalid Department ID;");
            return;
        }

        if(!_service.Create(title, departmentId, manager_id)){
            System.out.println("\u001B[31mFailed to create major\u001B[0m");
            return;
        }

        System.out.println("Major Created Successfully!");

        _scanner.nextLine();
        new Majors().Exec();
    }
}
