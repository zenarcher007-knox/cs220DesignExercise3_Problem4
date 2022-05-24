package spaceReserver;

import java.util.LinkedList;
import java.util.PriorityQueue;

// There was already an interactive map. This is the system that connects, interacts, and
// works with it to manage scheduling. It cannot work without the map.
public class SchedulingSystem {
  private InteractiveMap iMap;
  private PriorityQueue<ReservationTicket> masterQueue; // the tickets? or......
  public SchedulingSystem(InteractiveMap map) {
    this.iMap = map;
    this.masterQueue = new PriorityQueue<ReservationTicket>();
  }
  
  // Verifies that the ticket is filled out correctly, or sends a message to the person if not.
  public boolean verifyTicket(ReservationTicket ticket) {
    Building building;
    try {
      building = iMap.getBuilding(ticket.readBuildingName());
    } catch(IllegalArgumentException e) {
      ticket.readContactAddress().sendMessage("Your request for room " + ticket.readRoomNumber() + " in the " + ticket.readBuildingName() + 
          " building was ignored because of an invalid building name. Please fill out and resubmit a new request. Thank you.");
      return false;
    }
    
    try {
      building.getRoom(ticket.readRoomNumber());
    } catch(IllegalArgumentException e) {
      ticket.readContactAddress().sendMessage("Your request for room " + ticket.readRoomNumber() + " in the " + ticket.readBuildingName() + 
          " building was ignored because of an invalid room number. Please fill out and resubmit a new request. Thank you.");
      return false;
    }
    return true;
  }
  
  // Reserve a room based on the ReservationTicket. The room should be available the next morning,
  // or the soonest day available
  public void submitTicket(ReservationTicket r) {
    masterQueue.add(r);
  }
  
  // This is to be invoked each morning. Schedules the next round of people to as many rooms
  // as can fit, prioritizing earlier schedule dates and higher ranks.
  public void processRequests() {
    // First, unreserve all rooms from the previous day
    for(Room r : iMap.getAllRooms()) {
      r.setReserved(false);
    }
    
    LinkedList<ReservationTicket> overflowList = new LinkedList<ReservationTicket>();
    // Now, process ReservationTickets to reserve as many rooms as can fit
    while(! masterQueue.isEmpty()) {
      ReservationTicket ticket = masterQueue.poll();
      /* Read the ticket and reserve the room if possible, if not, it will be put in the overflow
         list, put back into the queue, and reserved at the next available time. */
      
      // Tickets are guaranteed to be valid beause they are verified immediately at submission.
      Building building = iMap.getBuilding(ticket.readBuildingName());
      Room room = building.getRoom(ticket.readRoomNumber());
      
      // Check if it is reseved. Enqueue the ticket for later if so.
      if(room.isReserved()) {
        overflowList.add(ticket);
        ticket.readContactAddress().sendMessage("Your room " + ticket.readRoomNumber() + " in building " + ticket.readBuildingName() + " has been enqueued for later because of a conflict.");
        continue;
      }
      
      ticket.readContactAddress().sendMessage("Your room " + ticket.readRoomNumber() + " in building " + ticket.readBuildingName() + " is ready!");
      room.setReserved(true);
    }
    
    // Add all overflow tickets back into the queue for the next day
    for(ReservationTicket ticket : overflowList) {
      masterQueue.add(ticket);
    }
  }
}
