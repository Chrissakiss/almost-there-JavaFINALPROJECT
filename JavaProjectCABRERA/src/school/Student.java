package school;
import util.Displayable;

public class Student extends Person implements Displayable {
    public int studentId;
    private int finalGrade;

    public Student() {
    }
    public Student(String firstName, String lastName) {
        super(firstName, lastName);
    }
    public Student(String firstName, String lastName, int studentId, int finalGrade) {
        super(firstName, lastName);
        setStudentId(studentId);
        setFinalGrade(finalGrade);
    }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getFinalGrade() { return finalGrade; }
    public void setFinalGrade(int finalGrade) {
        this.finalGrade = finalGrade;
    }

    public String display() {
        return "Student ID: " + studentId + "  " + getFullName() +
                " Final Grade: " + finalGrade;
    }
    public String display(String firstName, String lastName, int studentId, int finalGrade) {
        return  "Student ID: " + studentId + "   " + firstName + " " + lastName +
                "   " + " Final Grade: " + finalGrade;
    }
}
