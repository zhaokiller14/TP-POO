import java.util.ArrayList;
import java.util.List;
class Teacher extends Person {
    private int teacherId;
    List<Subject> subjects;

    public Teacher(String name, int age, int teacherId) {
        super(name, age);
        this.teacherId = teacherId;
        this.subjects = new ArrayList<>();
    }

    public void assignSubject(Subject subject) {
        subjects.add(subject);
    }
    public int getId() {
        return teacherId;
    }
}