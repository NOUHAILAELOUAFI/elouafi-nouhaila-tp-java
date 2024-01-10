package sge;

import sge.interfaces.Menu;
import sge.interfaces.MenuOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MenuManager implements MenuOption {
    private static MenuManager instance;
    private static final Scanner _scanner = new Scanner(System.in);

    private final List<Menu> menus = new ArrayList<>();

    private MenuManager(){}

    public static MenuManager getInstance() {
        if(instance == null){
            instance = new MenuManager();
        }
        return  instance;
    }

    public void AddMenu(Menu menu){
        this.menus.add(menu);
    }

    @Override
    public String GetName() {
        return "Back";
    }

    @Override
    public void Exec(){
        for(int i = 0; i<this.menus.size() ;i++){
            System.out.printf("%d. %s.\n", i, this.menus.get(i).GetName());
        }
        System.out.print("Choose a menu: ");
        int menu = _scanner.nextInt();

        try{
            this.menus.get(menu).Exec();
        }catch (IndexOutOfBoundsException exception){
            System.out.println(exception.getMessage());
            this.Exec();
        }
    }
}
