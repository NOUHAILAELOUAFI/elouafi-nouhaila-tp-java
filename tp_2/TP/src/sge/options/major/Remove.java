package sge.options.major;

import sge.interfaces.MenuOption;
import sge.services.MajorService;

import java.util.Scanner;

public class Remove implements MenuOption {
    private static final Scanner _scanner = new Scanner(System.in);
    private static final MajorService _service = MajorService.getInstance();

    @Override
    public void Exec() {
        System.out.print("Enter id: ");
        if( _service.Remove(_scanner.nextInt())){
            System.out.println("Major Removed");
        }else{
            System.out.println("Failed to remove Department");
        }
    }
}
