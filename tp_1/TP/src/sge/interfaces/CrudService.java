package sge.interfaces;

import java.util.List;

public interface CrudService<T> {
    List<T> GetAll();
    T Get(int index);
    boolean Remove (int index);
    boolean Remove(T object);

}
