package rvg.sclmngmtsstm.modals;

public class CourseInfoModal {
    public String courseName, teacher, schedule;
    public int numOfStudInrolled;
    public int ID;

    public CourseInfoModal(int ID, String courseName, String teacher, String schedule, int numOfStudInrolled) {
        this.ID = ID;
        this.courseName = courseName;
        this.teacher = teacher;
        this.schedule = schedule;
        this.numOfStudInrolled = numOfStudInrolled;
    }
    public CourseInfoModal(String courseName, String teacher, String schedule, int numOfStudInrolled) {
        this.courseName = courseName;
        this.teacher = teacher;
        this.schedule = schedule;
        this.numOfStudInrolled = numOfStudInrolled;
    }
}
