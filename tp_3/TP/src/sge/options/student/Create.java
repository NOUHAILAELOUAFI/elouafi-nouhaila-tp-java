package sge.options.student;

import sge.entities.Major;
import sge.interfaces.MenuOption;
import sge.menus.Students;
import sge.services.MajorService;
import sge.services.StudentService;

import java.util.Scanner;

public class Create implements MenuOption {
    private static final Scanner _scanner = new Scanner(System.in);
    private static final StudentService _service = StudentService.getInstance();
//    @Override
//    public void Exec() {
//        System.out.print("Enter student first name: ");
//        String firstName = _scanner.nextLine();
//        System.out.print("Enter student last name: ");
//        String lastName = _scanner.nextLine();
//        System.out.print("Enter student email: ");
//        String email = _scanner.nextLine();
//        int apogee, majorId;
//        do{
//            try{
//                System.out.print("Enter student's apogee: ");
//                apogee = _scanner.nextInt();
//                break;
//            }catch (Exception exception){
//                System.out.println("Invalid Integer, try again!");
//            }
//        }while (true);
//
//        do{
//            try{
//                System.out.print("Enter Major ID: ");
//                majorId = _scanner.nextInt();
//                break;
//            }catch (Exception exception){
//                System.out.println("Invalid Integer, try again!");
//            }
//        }while (true);
//
//        Major major = MajorService.getInstance().Get(majorId);
//        if(major == null){
//            System.out.println("Invalid Major ID");
//            return;
//        }
//
//        _service.Create(firstName, lastName, email, apogee, major);
//        _scanner.nextLine();
//        new Students().Exec();
//    }
}
