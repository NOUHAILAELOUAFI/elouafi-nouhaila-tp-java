package sge.interfaces;

import java.util.List;

public interface CrudService<T> {
    List<T> GetAll();
    T Get(long index);
    boolean Remove (long index);


}
