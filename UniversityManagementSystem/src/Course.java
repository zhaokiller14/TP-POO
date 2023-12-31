import java.util.ArrayList;
import java.util.List;
class Course {
    private String courseCode;
    List<Subject> subjects;
    private Teacher teacher;
    private Classroom classroom;
    private Group group;

    public Course(String courseCode) {
        this.courseCode = courseCode;
        this.subjects = new ArrayList<>();
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    public void courseDetails() {
        System.out.println("Course code: "+courseCode);
        System.out.println("Subjects: ");
        for (Subject S : subjects) {
            System.out.println(S.getSubjectName());
        }
        System.out.println("Teacher : "+teacher.getName());
        System.out.println("Classroom: "+classroom.roomNumber);
        System.out.println("Group: "+group.getGroupName());
    }
    public String getCode() {
        return courseCode;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public Classroom getClassroom() {
        return classroom;
    }
    public Group getGroup() {
        return group;
    }
}