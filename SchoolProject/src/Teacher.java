//This is the teacher class, it creates the 'teacher' object which stores some basic information about a person.
public class Teacher {
    private String firstName;
    private String lastName;
    private String subject;

    //This is the default constructor for a teacher
    Teacher() {
        firstName = "";
        lastName = "";
        subject = "None";
    }

    //This is the regular teacher constructor for teacher and takes input for all variables
    Teacher(String firstName, String lastName, String subject){
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    //When converted to a string, a teacher returns their name and subject
    public String toString() {
        return "Name: " + this.firstName + " " + this.lastName + "\tSubject: " + this.subject;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
