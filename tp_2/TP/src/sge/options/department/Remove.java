package sge.options.department;

import sge.interfaces.MenuOption;
import sge.services.DepartmentService;

import java.util.Scanner;

public class Remove implements MenuOption {
    private static final DepartmentService _service = DepartmentService.getInstance();
    private static final Scanner _scanner = new Scanner(System.in);

    @Override
    public void Exec() {
        System.out.print("Enter id: ");
        if( _service.Remove(_scanner.nextLong())){
            System.out.println("Department Removed");
        }else{
            System.out.println("Failed to remove Department");
        }
    }
}
