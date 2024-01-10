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

        Department department = manager.getDepartment();

        if(department != null){
            System.out.print("Teacher already manages a department, want to assign it to this major? [y/n]: ");
            String input = _scanner.nextLine();

            if(input.equalsIgnoreCase("y")){
                System.out.println("defaulting to teacher's Department;");
                department = manager.getDepartment();
                Major major = _service.Create(title, department, manager);

                int moduleCounts = 0;
                while (true){
//                    ModuleService.getInstance().Create()

                    moduleCounts++;
                    if(moduleCounts >= 12){
                        System.out.print("Do you wish to add another module [y/n]: ");
                        String choice = _scanner.nextLine();
                        if(choice.equalsIgnoreCase("n")){
                            break;
                        }
                    }


                    System.out.println("Enter Module title: ");
                    String moduleTitle = _scanner.nextLine();
                    ModuleService.getInstance().Create(moduleTitle,manager, major);

                }

                System.out.println("Major Created Successfully!");
                return;
            }

        }

        int department_id;
        do {
            try {
                System.out.print("Enter department ID: ");
                department_id = _scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                _scanner.nextLine();
            }
        }while (true);

        department = _departmentService.Get(department_id);
        if(department == null){
            System.out.println("Invalid Department ID;");
        }else{
            Major major = _service.Create(title, department, manager);
            int moduleCounts = 0;
            while (true){
//                    ModuleService.getInstance().Create()

                moduleCounts++;
                if(moduleCounts >= 12){
                    System.out.print("Do you wish to add another module [y/n]: ");
                    String choice = _scanner.nextLine();
                    if(choice.equalsIgnoreCase("n")){
                        break;
                    }
                }


                System.out.println("Enter Module title: ");
                String moduleTitle = _scanner.nextLine();
                ModuleService.getInstance().Create(moduleTitle,manager, major);

            }
            System.out.println("Major Created Successfully!");
        }



        _scanner.nextLine();
        new Majors().Exec();

    }
}
