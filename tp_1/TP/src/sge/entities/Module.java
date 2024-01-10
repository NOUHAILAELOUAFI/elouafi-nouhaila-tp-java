package sge.entities;

import java.util.ArrayList;
import java.util.List;

public class Module {
    private static final List<Module> instances = new ArrayList<>();
    private String title;
    private Major major;
    private Teacher teacher;

    public Module(){
        Module.instances.add(this);
    }

    public Module(String title, Major major, Teacher teacher){
        this.title = title;
        this.major = major;
        this.teacher = teacher;

        //static
        Module.instances.add(this);
    }

    public static List<Module> getInstances() {
        return Module.instances;
    }

    public String getTitle() {
        return title;
    }

    public Module setTitle(String title) {
        this.title = title;
        return this;
    }

    public Major getMajor() {
        return major;
    }

    public Module setMajor(Major major) {
        this.major = major;
        return this;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Module setTeacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    @Override
    public String toString() {
        return String.format("Module: %s", this.title);
    }
}
