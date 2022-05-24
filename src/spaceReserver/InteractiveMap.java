package spaceReserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class InteractiveMap {
  HashMap<String, Building> buildings;
  public InteractiveMap(String path) throws FileNotFoundException, InputMismatchException {
    // Loads map from the file. I thought about making the buildings an enum, but then it would be
    // hard to load the data from the file. Format is [building name] [room numbers...]*, separated
    // by new lines.
    buildings = new HashMap<String, Building>();
    Scanner sc = new Scanner(new File(path));
    while(sc.hasNextLine()) { // Populate map
      String buildingName = sc.next();
      LinkedList<Integer> roomNumbers = new LinkedList<>(); // List of room numbers to draw a building with
      while(sc.hasNextInt()) {
        int roomNumber = sc.nextInt();
        roomNumbers.add(roomNumber);
      }
      buildings.put(buildingName, new Building(buildingName, roomNumbers));
    }
    sc.close();
  }
  
  // I decided on an almost directory-like structure, since that is basically what
  // a (real-word) map is. This would be almost the equivalent of "ls."
  public Collection<Building> listBuildings() {
    return buildings.values();
  }
  
  // Directs you to the building by the specified name, or throws an exception
  // if nonexistant.
  public Building getBuilding(String name) {
    Building b = buildings.get(name);
    if(b == null)
      throw new IllegalArgumentException("The building \"" + name + "\" does not exist.");
    return b;
  }
  
  // Returns a collection of all rooms in the entire map
  public Collection<Room> getAllRooms() {
    HashSet<Room> r = new HashSet<Room>();
    for(Building b : listBuildings())
      r.addAll(b.listRooms());
    return r;
  }
}
