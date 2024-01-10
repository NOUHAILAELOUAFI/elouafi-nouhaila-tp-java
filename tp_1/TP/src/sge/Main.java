package sge;

import sge.interfaces.Menu;
import sge.interfaces.MenuOption;
import sge.menus.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MenuManager manager = MenuManager.getInstance();
        manager.AddMenu(new Departments());
        manager.AddMenu(new Teachers());
        manager.AddMenu(new Majors());
        manager.AddMenu(new Modules());
        manager.AddMenu(new Students());

        manager.AddMenu(new Menu() {
            @Override
            public String GetName() {
                return "Quit";
            }

            @Override
            public List<MenuOption> GetOptions() {
                return null;
            }

            @Override
            public void Exec() {
                Runtime.getRuntime().halt(0);
            }
        });

        manager.Exec();
    }
}