package sge.entities;

import java.util.ArrayList;
import java.util.List;

public class Module {
    private long id;
    private String title;
    private long majorId;
    private long teacherId;

    public Module(){
    }

    public Module(String title, long majorId, long teacherID){
        this.title = title;
        this.majorId = majorId;
        this.teacherId = teacherID;
    }

    public long getId(){
        return this.id;
    }

    public Module setId(long id){
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Module setTitle(String title) {
        this.title = title;
        return this;
    }

    public long getMajorId() {
        return this.majorId;
    }

    public Module setMajorId(long majorId) {
        this.majorId = majorId;
        return this;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public Module setTeacherId(long teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%d ~ %d ~ %d ~ %s", this.id, this.teacherId, this.majorId, this.title);
    }
}
