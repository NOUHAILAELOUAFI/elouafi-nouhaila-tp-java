package sge.options.teacher;

import sge.interfaces.MenuOption;
import sge.services.TeacherService;

import java.util.Scanner;

public class Remove implements MenuOption {
    private static final TeacherService _service = TeacherService.getInstance();
    private static final Scanner _scanner = new Scanner(System.in);

    @Override
    public void Exec() {
        System.out.print("Enter id: ");
        if( _service.Remove(_scanner.nextInt())){
            System.out.println("Teacher Removed");
        }else{
            System.out.println("Failed to delete Teacher");
        }
    }
}
