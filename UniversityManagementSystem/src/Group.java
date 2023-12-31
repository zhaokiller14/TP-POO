import java.util.ArrayList;
import java.util.List;
class Group {
    private String groupName;
    List<Student> students;
    
    public String getGroupName() {
        return groupName;
    }
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
            System.out.println(P.getName());
        }
    } 
}