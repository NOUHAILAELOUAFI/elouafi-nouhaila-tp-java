package sge.menus;

import sge.MenuManager;
import sge.interfaces.Menu;
import sge.interfaces.MenuOption;
import sge.options.module.ListAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Modules implements Menu {
    private static final Scanner _scanner = new Scanner(System.in);


    @Override
    public List<MenuOption> GetOptions() {
        List<MenuOption> options = new ArrayList<>();
        options.add(new ListAll());

        options.add(MenuManager.getInstance());
        return options;
    }

    @Override
    public void Exec() {
        for(int i = 0; i< this.GetOptions().size(); i++){
            System.out.printf("%d. %s.\n", i, this.GetOptions().get(i).GetName());
        }

        System.out.print("Choose a menu: ");
        int menu = _scanner.nextInt();

        try{
            this.GetOptions().get(menu).Exec();
        }catch (IndexOutOfBoundsException exception){
            System.out.println(exception.getMessage());
            this.Exec();
        }

        _scanner.nextLine();
        MenuManager.getInstance().Exec();
    }
}
