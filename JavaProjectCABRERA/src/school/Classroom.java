package school;
import util.Displayable;
import java.util.ArrayList;

public class Classroom implements Displayable {
    private int roomNumber;
    Displayable<Teacher> teacher;
    ArrayList<Displayable<Student>> students = new ArrayList<>();

    public Classroom() {
    }
    public Classroom(int roomNumber, Displayable<Teacher> teacher, ArrayList<Displayable<Student>> students) {
        this.roomNumber = roomNumber;
        this.teacher = teacher;
        this.students = students;
    }


    public int getRoomNumber() { return roomNumber; }
    public Displayable getTeacher() { return teacher; }

    public ArrayList<Displayable<Student>> getStudents(ArrayList<Displayable<Student>> students) {
        return students;
    }

    public String display() {
        return "Room Number: " + roomNumber;
    }
}
