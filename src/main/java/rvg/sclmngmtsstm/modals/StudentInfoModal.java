package modals;

public class StudentInfoModal {
    public String name, grade, level, address, contact;
    public int ID;
    public StudentInfoModal(int ID,String name,String grade,String level,String address,String contact){
        this.ID = ID;
        this.name = name;
        this.grade = grade;
        this.level = level;
        this.address = address;
        this.contact = contact;
    }
    public StudentInfoModal(String name,String grade,String level,String address,String contact){
        this.name = name;
        this.grade = grade;
        this.level = level;
        this.address = address;
        this.contact = contact;
    }
}
