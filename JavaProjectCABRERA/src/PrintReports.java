import school.*;
import util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PrintReports {
    public static void main(String[] args) throws IOException {
        System.out.print("First you need to create a Classroom; press return (twice).");
        new PrintReports();
    }

    public PrintReports() throws IOException {
        BufferedReader ask = new BufferedReader(new InputStreamReader(System.in));
        String str = ask.readLine();
        ArrayList<Classroom> roomList = new ArrayList<>();


            Classroom room;
            char answer;
            do {
                int roomNumber;
                Displayable<Teacher> teacher;
                ArrayList<Displayable<Student>> students = new ArrayList<>();
                try {
                    room = (Classroom) enterClassroom();
                    roomNumber = room.getRoomNumber();
                    teacher = room.getTeacher();
                    students = room.getStudents(students);
                    Classroom newRoom = new Classroom(roomNumber, teacher, students);
                    roomList.add(newRoom);
                } catch (ClassCastException ccex) {
                    System.out.println(ccex.getMessage());
                }

                answer = KeyboardReader.getPromptedChar("Enter another Classroom? (Y/N): ");
                if (str.equalsIgnoreCase("N")) break;
            } while (answer == 'Y' || answer == 'y');

        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------");
        report(roomList);
        System.out.println("---------------------------------------------------------");
    }


    public Displayable <Classroom> enterClassroom() throws IOException, ClassCastException {
        ArrayList<Displayable<Student>> listStudents = new ArrayList<>();
        BufferedReader ask = new BufferedReader(new InputStreamReader(System.in));
        String str = ask.readLine();

        int roomNumber;
        do roomNumber = KeyboardReader.getPromptedInt("Enter room number: ",
                "Invalid format", new RoomValidator(),
                "Valid room numbers are integers >= 100"); while (roomNumber < 100);

        System.out.println();
        System.out.println("Now you need to enter a Teacher for the Classroom");
        Displayable<Teacher> prof = enterTeacher();

        System.out.println();
        System.out.println("Now you need to add Students for the Classroom");
        char answer;
        do {
            Displayable<Student> coed = enterStudent();
            listStudents.add(coed);
            answer = KeyboardReader.getPromptedChar("Enter another Student? (Y/N): ");
            if (str.equalsIgnoreCase("N")) break;
        } while (answer == 'Y' || answer == 'y');

        return new Classroom(roomNumber, prof, listStudents);
    }

    public Displayable<Teacher> enterTeacher() {
        String fName;
        String lName;
        String sub;

        fName = KeyboardReader.getPromptedString("Enter Teacher first name: ");
        lName = KeyboardReader.getPromptedString("Enter Teacher last name: ");
        sub = KeyboardReader.getPromptedString("Enter subject taught: ");
        Teacher teach = new Teacher(fName, lName, sub);
        Displayable<Teacher> prof = new Displayable<Teacher>() {
            @Override
            public String display() {
                return teach.display();
            }
        };
        return prof;
    }

    public Displayable enterStudent() {
        String fName;
        String lName;
        int studentId;
        int finalGrade;

        fName = KeyboardReader.getPromptedString("Enter Student first name: ");
        lName = KeyboardReader.getPromptedString("Enter Student last name: ");
        do {
            studentId = KeyboardReader.getPromptedInt("Enter Student ID: ",
                    "Invalid format", new IdValidator(),
                    "A student's ID must be greater than 0");
            if (studentId <= 0);
        } while (studentId <= 0);
        do {
            finalGrade = KeyboardReader.getPromptedInt("Enter Student final grade: ",
                    "Invalid format", new GradeValidator(),
                    "A student's final grade must be an integer between 0 and 100");
            if (finalGrade < 0 || finalGrade > 100);
        } while (finalGrade < 0 || finalGrade > 100);
        Student pupil = new Student(fName, lName, studentId, finalGrade);

        Displayable scholar = new Displayable<Student>() {
            @Override
            public String display() {
                return pupil.display();
            }
        };
        return scholar;
    }

    void report(ArrayList<? extends Displayable> Classroom) {
        ArrayList<Displayable> d = new ArrayList<>();
        Student me = new Student("Crissy", "Cabrera", 9, 89);
        Student her = new Student("Jane", "Cabrera", 1, 99);
        ArrayList<Displayable<Student>> room1 = new ArrayList<>();
        room1.add(me);
        room1.add(her);
        Displayable<Teacher> parrish = new Teacher("JoJo", "Meyer", "Guitar");
        Classroom studio = new Classroom(123, parrish, room1);

        d.add(studio);
        d.add(parrish);
        d.addAll(room1);

        StringBuffer result = new StringBuffer();
        for (Displayable<Classroom> rm : d) {
            System.out.println(rm.display());
        }
    }
}
