package sge.interfaces;

import java.util.List;

public interface Menu {
    default String GetName(){
        return this.getClass().getSimpleName().substring(0,1).toUpperCase() + this.getClass().getSimpleName().substring(1);
    };

    default List<MenuOption> GetOptions(){
        return null;
    };

    void Exec();
}
