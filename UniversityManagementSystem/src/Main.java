import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Create subjects
        Subject math = new Subject("MATH101", "Mathematics");
        Subject physics = new Subject("PHYS101", "Physics");

        // Create teachers
        Teacher mathTeacher = new Teacher("John Doe", 35, 1);
        mathTeacher.assignSubject(math);

        Teacher physicsTeacher = new Teacher("Jane Smith", 40, 2);
        physicsTeacher.assignSubject(physics);

        // Create students
        Student student1 = new Student("Alice Johnson", 20, 1001);
        Student student2 = new Student("Bob Williams", 21, 1002);

        // Enroll students in courses
        Course mathCourse = new Course("MATH101");
        mathCourse.addSubject(math);
        mathCourse.setTeacher(mathTeacher);

        Course physicsCourse = new Course("PHYS101");
        physicsCourse.addSubject(physics);
        physicsCourse.setTeacher(physicsTeacher);

        student1.enrollCourse(mathCourse);
        student1.enrollCourse(physicsCourse);

        student2.enrollCourse(mathCourse);

        student1.courseDisplay();
        student2.courseDisplay();
        
        // Create clubs
        Club mathClub = new Club("Math Club");
        mathClub.addMember(student1);
        mathClub.addMember(mathTeacher);

        Club physicsClub = new Club("Physics Club");
        physicsClub.addMember(student2);
        physicsClub.addMember(physicsTeacher);

        // Create groups
        Group mathGroup = new Group("Math Group");
        mathGroup.addStudent(student1);

        Group physicsGroup = new Group("Physics Group");
        physicsGroup.addStudent(student2);

        mathGroup.displayStudents();
        physicsGroup.displayStudents();
        // Create events
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date eventDate;
        try {
            eventDate = dateFormat.parse("2023-01-15");
            Club eventOrganizer = mathClub; // Choose a club as the organizer
            Event mathEvent = new Event("Math Event", eventDate, 50.0, eventOrganizer);
            mathEvent.addParticipant(student1);
            mathEvent.addParticipant(student2);
            mathEvent.displayParticipants();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
    }

}
