import java.util.Date;
import java.util.List;
import java.util.ArrayList;

class Event {
    String eventName;
    Date eventDate;
    List<Person> participants;
    Club organizer;
    double participationFee;

    public Event(String eventName, Date eventDate,double fees,Club organizer) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.participants = new ArrayList<>();
        participationFee=fees;
        this.organizer=organizer;
    }

    public void addParticipant(Person person) {
        System.out.println(person.name+" wants to participate in event: "+eventName);
        participants.add(person);
        feesManager(person);
    }
    public void feesManager(Person P) {
        if (organizer.isMember(P)) {
            System.out.println("Since you're a member you only have to pay "+participationFee*0.8+" Dinars");
        } else {
            System.out.println("Participation fees are "+ participationFee);
        }
    }
    public void displayParticipants() {
        System.out.println("Event "+eventName+"by club "+organizer.clubName+" Participants are: ");
        for (Person P : participants) {
            System.out.println(P.name);
        }
    } 
}