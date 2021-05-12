//This is the main class, it is the place in which all other classes are being used.
public class Main {
    public static void main(String[] args) {
        School example = new School();
        for (int i = 0; i < 10; i++) example.addStudent(i,new Student());
        for (int i = 0; i < 3; i++) example.addTeacher(i, new Teacher());
        example.showStudents();
        example.showTeachers();
        for (int i = 0; i < 2; i++) example.removeStudent(i);
        example.removeTeacher(0);
        example.showStudents();
        example.showTeachers();
    }
}
