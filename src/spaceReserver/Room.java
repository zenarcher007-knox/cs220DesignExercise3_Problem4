package spaceReserver;

public class Room {
  private boolean reserved;
  private Building parentBuilding;
  private int roomNumber;
  public Room(Building parentBuilding, int roomNumber) {
    this.parentBuilding = parentBuilding;
    this.roomNumber = roomNumber;
    reserved = false;
  }
  
  public boolean isReserved() { return reserved; }
  public int getRoomNumber() { return roomNumber; }
  public Building getParentBuilding() { return parentBuilding; }
  
  // This is basically a sign outside the door, telling if it is reserved.
  // A person can go to a room and flip the sign to confuse people, and they may be expelled or something,
  // but it does not really matter in terms of the scheduling system.
  public void setReserved(boolean reserved) {
    this.reserved = reserved;
  }
  
  @Override
  public String toString() {
    return "Room #" + roomNumber;
  }
  
  
  // I decided that the type of room was not exactly necessary for this implementation.
  
}
