import java.util.ArrayList;
import java.util.List;
class Course {
    String courseCode;
    List<Subject> subjects;
    Teacher teacher;
    Classroom classroom;
    Group group;

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
            System.out.println(S.subjectName);
        }
        System.out.println("Teacher : "+teacher.name);
        System.out.println("Classroom: "+classroom.roomNumber);
        System.out.println("Group: "+group.groupName);
    }
}