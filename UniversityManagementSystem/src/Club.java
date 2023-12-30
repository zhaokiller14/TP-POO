import java.util.ArrayList;
import java.util.List;
class Club {
    String clubName;
    List<Person> members;

    public Club(String clubName) {
        this.clubName = clubName;
        this.members = new ArrayList<>();
    }

    public void addMember(Person person) {
        members.add(person);
    }
    public boolean isMember(Person P) {
        return members.contains(P);
    }
    public void displayMembers() {
        System.out.println("Club "+clubName+" members are: ");
        for (Person P : members) {
            System.out.println(P.name);
        }
    } 
}