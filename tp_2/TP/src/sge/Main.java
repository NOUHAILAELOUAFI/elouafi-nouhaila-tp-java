package sge;

import sge.data.DataContext;
import sge.interfaces.Menu;
import sge.menus.*;


import java.io.IOException;

import static sge.Utils.loadEntities;
import static sge.Utils.verifySaveFileIntegrity;

public class Main {
    public static void main(String[] args) throws IOException {

        MenuManager menuManager = MenuManager.getInstance();
        menuManager.AddMenu(new Departments());
        menuManager.AddMenu(new Teachers());
        menuManager.AddMenu(new Majors());
        menuManager.AddMenu(new Modules());
        menuManager.AddMenu(new Students());
        menuManager.AddMenu(new Menu() {
            @Override
            public String GetName() {
                return "Quit";
            }

            @Override
            public void Exec() {
                Runtime.getRuntime().halt(0);
            }
        });

        verifySaveFileIntegrity("teacher.data", "department.data", "major.data", "module.data", "student.data");

        if(loadEntities("sge.entities")){
            System.out.println("Synced!");
        }

        menuManager.Exec();
    }
}