package sge.entities;

import sge.services.ModuleService;

import java.util.ArrayList;
import java.util.List;

public class Major {
    private long id;
    private String title;
    private Long responsibleId;
    private Long departmentId;

    public Major(){}

    public Major(String title, long managerId, long departmentId){
        this.title = title;
        this.responsibleId = managerId;
        this.departmentId = departmentId;
    }

    public long getId(){
        return this.id;
    }

    public Major setId(long id){
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Major setTitle(String title) {
        this.title = title;
        return this;
    }

    public long getResponsibleId() {
        return this.responsibleId;
    }

    public Major setResponsibleId(long responsibleId) {
        this.responsibleId = responsibleId;
        return this;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public Major setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%d ~ %d ~ %d ~ %s", this.id, this.responsibleId, this.departmentId, this.title);
    }
}
