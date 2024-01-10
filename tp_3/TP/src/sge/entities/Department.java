package sge.entities;

public class Department{

    private long id;
    private long managerId;
    private String title;

    public Department(){
    }

    public Department(String title, long managerId){
        this.title = title;
        this.managerId = managerId;
    }

    public long getId() {
        return id;
    }

    public Department setId(long id){
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Department setTitle(String _title) {
        this.title = _title;
        return this;
    }
    public Long getManagerId() {
        return managerId;
    }

    public Department setManagerId(Long managerId) {
        this.managerId = managerId;
        return  this;
    }

    @Override
    public String toString() {
        return String.format("%d ~ %d ~ %s", this.id, this.managerId, this.title);
    }


}
