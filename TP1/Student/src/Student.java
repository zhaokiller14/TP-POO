import java.util.ArrayList; 

public class Student {

    private int studentID;
    private String firstName;
    private String lastName;
    private ArrayList<Course> courses = new ArrayList<Course>();
    public  Student(int ID,String fn, String ln,ArrayList<Course> c) {
        studentID=ID;
        firstName=fn;
        lastName=ln;
        courses = c;
    }
    public int getStudentId() {
        return studentID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public ArrayList<Course> getCourses() {
        return courses;
    }
    public void enroll(Course c) {
        courses.add(c);
    }
    public class Course {
        private int courseId;
        private String courseName;
        private Instructor instructor;
        public Course(int ID,String cn, Instructor i) {
            courseId=ID;
            courseName=cn;
            instructor=i;
        }
        public int getCourseId() {
            return courseId;
        }
        public String getCourseName() {
            return courseName;
        }
        public Instructor getInstructor() {
            return instructor;
        }
    }
    public class Instructor {
        private int instructorId;
        private String firstName;
        private String lastName;
        public Instructor(int ID,String fn,String ln) {
            studentID=ID;
            firstName=fn;
            lastName=ln;
        } 
        public int getInstructorId() {
            return instructorId;
        }
        public String getFirstName() {
            return firstName;
        }
        public String getLastName() {
            return lastName;
        }
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
} 
