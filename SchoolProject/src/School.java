import java.util.ArrayList;

//This is the school class, it creates the 'school' object which combines teachers and students into a larger list along with some other variables about the details of the school.
public class School {
    private ArrayList<Teacher> teachers;
    private ArrayList<Student> students;
    private ArrayList<String> courses;
    private String name;
    private String location;
    private int studentCount;

    //The default constructor for a school which creates three new arrays
    School(){
        teachers = new ArrayList<>();
        students = new ArrayList<>();
        courses = new ArrayList<>();
        name = "";
        location = "Vancouver";
        studentCount = 0;
    }

    //The normal school constructor takes input for all variables except the courses (which it gets from the subjects taught by the teachers in the 'teachers' list) and student count (which is simply the number of students).
    School(ArrayList<Teacher> teachers, ArrayList<Student> students, ArrayList<String> courses, String name, String location) {
        this.teachers = teachers;
        this.students = students;
        for (int i = 0; i < teachers.size(); i++) {
            Teacher current = teachers.get(i);
            if (!courses.contains(current.getSubject())) courses.add(current.getSubject());
        }
        this.name = name;
        this.location = location;
        studentCount = students.size();
    }

    //This method adds a student to the list at a specified position and increases the student count by 1.
    public void addStudent(int position, Student student) {
        students.add(position, student);
        studentCount++;
    }

    //This method adds a teacher to the list at a specified position and adds their course if it was not previously on the course list.
    public void addTeacher(int position, Teacher teacher) {
        teachers.add(position, teacher);
        if (!courses.contains(teacher.getSubject())) courses.add(teacher.getSubject());
    }

    //This method removes a student from the list at the specified position and decreases the student count by 1.
    public void removeStudent(int position) {
        students.remove(position);
        studentCount--;
    }

    //This method removes a teacher from the list at the specified position, then removes their subject from the course list and finally adds the course back if another teacher teaches it.
    public void removeTeacher(int position) {
        Teacher current = teachers.get(position);
        String subject = current.getSubject();
        courses.remove(subject);
        teachers.remove(position);
        for (int i = 0; i < teachers.size(); i++) {
            current = teachers.get(i);
            if (current.getSubject().equals(subject)) {
                courses.add(subject);
                break;
            }
        }
    }

    //Prints all students in string format
    public void showStudents() {
        System.out.println(students.toString());
    }

    //Prints all teachers in string format
    public void showTeachers() {
        System.out.println(teachers.toString());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStudentCount() {
        return studentCount;
    }
}
