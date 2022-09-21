package sample;

public class Course {
    // Globals
    public static final String[] DEPARTMENTS = new String[]{"Computer Science", "Chemistry", "Physics", "Mathematics", "Botany", "Zoology"};
    public static final String[] DEP_CODES = new String[]{"CS", "CHEM", "PHYS", "MATH", "BTNY", "ZOO"};

    // variables
    private String department;
    private String subject;
    private String number;
    private String title;
    private String credits;

    //methods
    public Course(String department, String subject, String number, String title, String credits) {
        this.department = department;
        this.subject = subject;
        this.number = number;
        this.title = title;
        this.credits = credits;
    }

    public String getDepartment() {
        return department;
    }
    public String  getNumber() {
        return number;
    }
    public String getSubject() {
        return subject;
    }
    public String getTitle() {
        return title;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String toString(){
        return subject +
                " " + number +
                " " + title +
                " / " + credits + " credits.";
    }
}
