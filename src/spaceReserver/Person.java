package spaceReserver;

// A person
public class Person {
  String name;
  private Rank rank;
  public Person(String name, Rank rank) {
    this.name = name;
    this.rank = rank;
  }
  
  // Updates the person's rank
  public void setRank(Rank r) {
    this.rank = r;
  }
  
  public String getName() {
    return name;
  }
  
  // Reads this person's rank
  public Rank getRank() {
    return rank;
  }
  
  // Tells this person something. Basically their email / phone, etc.
  public void sendMessage(String msg) {
    System.out.println("Person \"" + name + "\" recieved a message: " + msg);
  }
  
  // Asks the person to accurately fill out a ReservationTicket for the specified Room.
  public ReservationTicket fillOutTicket(Room room) {
    try {
      Thread.sleep(100); // Fill it out really fast, but still really slow in terms of a computer
    } catch (InterruptedException e) { e.printStackTrace();}
    return new ReservationTicket(getName(), getRank(), room.getParentBuilding().getName(), room.getRoomNumber(), this);
  }
}
