//This is the teacher class, it creates the 'student' object which stores some basic information about a student and creates a unique number for them.
public class Student {
    private String firstName;
    private String lastName;
    private int grade;
    private static int numCounter = 1000;
    private int studentNum;

    //This is the default constructor for a student, including an increasing student number
    Student() {
        firstName = "";
        lastName = "";
        grade = 12;
        studentNum = numCounter;
        numCounter++;
    }

    //This is the regular constuctor for a student, it generates student numbers in the same way put takes input for the other variables.
    Student(String firstName, String lastName, int grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.studentNum = numCounter;
        numCounter++;
    }

    //When converting a Student to a string, it will return their name and grade.
    public String toString() {
        return "Name: " + this.firstName + " " + this.lastName + "\tGrade: " + this.grade;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getStudentNum() {
        return studentNum;
    }
}
