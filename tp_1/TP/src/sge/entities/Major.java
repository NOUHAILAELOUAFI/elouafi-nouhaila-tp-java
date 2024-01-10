package sge.entities;

import sge.services.ModuleService;

import java.util.ArrayList;
import java.util.List;

public class Major {
    private static final List<Major> instances = new ArrayList<>();
    private String title;
    private Teacher responsible;
    private Department department;

    public Major(){
        Major.instances.add(this);
    }

    public Major(String title, Teacher manager, Department department){
        this.title = title;
        this.responsible = manager;
        this.department = department;

        //static
        Major.instances.add(this);
    }

    public static List<Major> getInstances(){
        return  Major.instances;
    }
    public String getTitle() {
        return title;
    }

    public Major setTitle(String title) {
        this.title = title;
        return this;
    }

    public Teacher getResponsible() {
        return responsible;
    }

    public Major setResponsible(Teacher responsible) {
        this.responsible = responsible;
        return this;
    }

    public Department getDepartment() {
        return department;
    }

    public Major setDepartment(Department department) {
        this.department = department;
        return this;
    }

    @Override
    public String toString() {
        return String.format("Major: %s, %d modules",
                this.title,
                ModuleService.getInstance().GetAll().stream()
                        .filter((module -> module.getMajor().equals(this)))
                        .toList()
                        .size());
    }
}
