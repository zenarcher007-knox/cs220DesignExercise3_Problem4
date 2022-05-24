package spaceReserver;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Building {
  public String name;
  public HashMap<Integer, Room> rooms;
  public Building(String name, List<Integer> roomNumbers) {
    this.name = name;
    this.rooms = new HashMap<Integer, Room>();
    
    // Populate with rooms; don't expose this to map users in its general usage.
    for(int num : roomNumbers) {
      rooms.put(num, new Room(this, num));
    }
  }
  
  // Lists all rooms in this building
  public Collection<Room> listRooms() {
    return rooms.values();
  }
  
  // Gets the Room from the specified room number, or throws an exception if nonexistent.
  public Room getRoom(int roomNumber) {
    Room r = rooms.get(roomNumber);
    if(r == null)
      throw new IllegalArgumentException("The room number queried in building \"" + name + "\" does not exist.");
    return r;
  }
  
  public String getName() { return name; }
  
  public String toString() {
    return "Building: " + name;
  }
  
}
