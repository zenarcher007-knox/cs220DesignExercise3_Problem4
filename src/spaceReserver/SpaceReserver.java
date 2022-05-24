package spaceReserver;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;

// Main demonstration class
public class SpaceReserver {

  public static void main(String[] args) throws InputMismatchException, FileNotFoundException {
    InteractiveMap iMap = new InteractiveMap("src/spaceReserver/Buildings.txt");
    
    SchedulingSystem system = new SchedulingSystem(iMap);
    
    
    System.out.println("\nDay #1:");
    
    /********* Day 1: Example of exploring the map, and filling out a ticket to reserve a room 
     * This is the most general use case: **********/
    { 
      Person John = new Person("John", Rank.student);
      // John explores the map...
      System.out.println(iMap.listBuildings()); // John looks at the available buildings,
      Building building = iMap.getBuilding("SMC"); // and selects one
      
      System.out.println(building.listRooms()); // John looks at the available rooms in the SMC,
      Room room = building.getRoom(208);
      ReservationTicket JohnsTicket = John.fillOutTicket(room);
      system.submitTicket(JohnsTicket); // Day 1
    }
    
    { // Day 2: Example with many people wanting to share the same rooms...
      system.processRequests(); // This is called in the morning of the next day...
    
      Person Alice = new Person("Alice", Rank.student);
      Person Jacob = new Person("Jacob", Rank.student);
      Person ProfA = new Person("ProfA", Rank.professor);
      Person ProfB = new Person("ProfB", Rank.professor);
      Person Deanial = new Person("Deanial", Rank.dean);
      Person Manny = new Person("Manny", Rank.manager);
      Person Manuel = new Person("Manuel", Rank.manager);
      Person Presiante = new Person("Presiante", Rank.president);
    
      // Alice and Jacob both want the same room, but it's first come-first serve...
      Room room208 = iMap.getBuilding("SMC").getRoom(208);
      system.submitTicket(Alice.fillOutTicket(room208));
      system.submitTicket(Jacob.fillOutTicket(room208));
      
      /// So does Presiante...
      system.submitTicket(Presiante.fillOutTicket(room208));
      
      // Some others...
      // Note that I just used a random number generator to fill out available rooms
      // in each building. None of this (except for the building names) reflects real-life
      // scenarios...
      Room art177 = iMap.getBuilding("Whitcomb_Art_Center").getRoom(177);
      system.submitTicket(Deanial.fillOutTicket(art177));
      system.submitTicket(Manny.fillOutTicket(art177));
      system.submitTicket(Presiante.fillOutTicket(art177));
      
      Room art180 = iMap.getBuilding("Whitcomb_Art_Center").getRoom(180);
      system.submitTicket(Manuel.fillOutTicket(art180));
      
      Room ford104 = iMap.getBuilding("Ford_Center").getRoom(104);
      Room ford99 = iMap.getBuilding("Ford_Center").getRoom(99);
      system.submitTicket(ProfA.fillOutTicket(ford104)); // These two don't conflict
      system.submitTicket(ProfB.fillOutTicket(ford99));
    
      System.out.println("\nDay #2:");
      system.processRequests();
      
      // Now Deanial wants SMC #208 too!
      system.submitTicket(Deanial.fillOutTicket(room208));
    }
    
    // With this large influx of scheduling, how does this play out after a matter of days?
    
    for(int i = 3; i <= 6; ++i) {
      System.out.println();
      System.out.println("Day #" + i + ":");
      system.processRequests();
    }
    
    
    
    
    { // Additional small-scale test:
      // Test based on rank...
      Person Jacob = new Person("Jacob", Rank.student);
      Person Deanial = new Person("Deanial", Rank.dean);
      Room room208 = iMap.getBuilding("SMC").getRoom(208);
      system.submitTicket(Jacob.fillOutTicket(room208));
      system.submitTicket(Deanial.fillOutTicket(room208));
      System.out.println("\n\n\n[Day 7]:");
      system.processRequests();
      System.out.println("[Day 8]:");
      system.processRequests();
      
      // Test based on time...
      Person Alice = new Person("Alice", Rank.student);
      // Alice fills out her ticket before Jacob...
      ReservationTicket Aticket = Alice.fillOutTicket(room208);
      ReservationTicket Jticket = Jacob.fillOutTicket(room208);
      system.submitTicket(Jticket); // ...but they were submitted in a different order.
      system.submitTicket(Aticket);
      System.out.println("\n[Day 9]:");
      system.processRequests();
      System.out.println("[Day 10]:");
      system.processRequests();
    }
    
  }
  
 

}
