package sge.entities;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private static final List<Department> instances = new ArrayList<>();
    private String title;
    private Teacher manager;

    public Department(){
        Department.instances.add(this);
    }

    public Department(String title, Teacher manager){
        this.title = title;
        this.manager = manager;
        
        //assign Department in teacher instance due to nature of [1 to 1: relationship]
        if(manager.getDepartment() == null){
            manager.setDepartment(this);
        }
        //static
        Department.instances.add(this);
    }

    public static List<Department> getInstances() {
        return instances;
    }

    public String getTitle() {
        return title;
    }

    public Department setTitle(String _title) {
        this.title = _title;
        return this;
    }

    public Teacher getManager() {
        return manager;
    }

    public Department setManager(Teacher _manager) {
        this.manager = _manager;
        return  this;
    }


    @Override
    public String toString() {
        return String.format("Department: %s", this.title);
    }
}
