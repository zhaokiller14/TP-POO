import java.util.ArrayList;
import java.util.List;
class Group {
    String groupName;
    List<Student> students;

    public Group(String groupName) {
        this.groupName = groupName;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }
    public void displayStudents() {
        System.out.println("Group "+groupName+" students are: ");
        for (Person P : students) {
            System.out.println(P.name);
        }
    } 
}