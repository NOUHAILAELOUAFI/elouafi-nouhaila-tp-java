package sge.interfaces;

public interface MenuOption {
    default String GetName(){
        return this.getClass().getSimpleName().substring(0,1).toUpperCase()
                +
                this.getClass().getSimpleName().substring(1);
    };


    default void Exec(){};
}
