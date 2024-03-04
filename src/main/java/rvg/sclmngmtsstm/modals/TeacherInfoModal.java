package rvg.sclmngmtsstm.modals;

public class TeacherInfoModal {
    public String name, subject, contact;
    public int ID;
    public TeacherInfoModal(int ID,String name, String subject, String contact){
        this.ID = ID;
        this.name = name;
        this.subject = subject;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getContact() {
        return contact;
    }

    public int getID() {
        return ID;
    }

    public TeacherInfoModal(String name, String subject, String contact){
        this.name = name;
        this.subject = subject;
        this.contact = contact;
    }
}
