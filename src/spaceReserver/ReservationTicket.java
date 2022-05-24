package spaceReserver;

import java.time.Instant;

// You cannot reserve a room without first filling out this ticket. Being a digital ticket,
// it stores the information you provided, along with other necessary information.
public class ReservationTicket implements Comparable<ReservationTicket> {
  private String name;
  private Rank rank;
  private String buildingName;
  private int roomNumber;
  private Instant timeStamp;
  private Person contactAddress; // This is a pointer to the Person, representing a means to contact them by.
  public ReservationTicket(String name, Rank rank, String buildingName, int roomNumber, Person contactAddress) {
    this.name = name;
    this.rank = rank;
    this.buildingName = buildingName;
    this.roomNumber = roomNumber;
    this.contactAddress = contactAddress;
    // Also store the exact time this was filled out
    timeStamp = Instant.now();
  }
  
  public String readName() { return name; }
  public Rank readRank() { return rank; }
  public String readBuildingName() { return buildingName; }
  public int readRoomNumber() { return roomNumber; }
  public Person readContactAddress() { return contactAddress; }
  
  @Override
  public int compareTo(ReservationTicket o) {
    if(rank != o.rank) {
      return o.rank.ordinal() - rank.ordinal();  // The higher rank the better
    } else {
      return timeStamp.compareTo(o.timeStamp);
    }
  }
  
  
}
