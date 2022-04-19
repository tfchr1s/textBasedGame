import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Room implements Serializable {
    private final int roomID;
    private final String description;
    private boolean beenVisited;
    private final HashMap<String, Integer> roomsMap;
    private String blockedDirection;
    private String blockedCondition;
    private String conditionItem;
    public int getRoomID() {
        return roomID;
    }

    public String getDescription() {
        return description;
    }

    public boolean hasBeenVisited() {
        return beenVisited;
    }

    public void setBeenVisited(boolean beenVisited) {
        this.beenVisited = beenVisited;
    }

    public HashMap<String, Integer> getRoomsMap() {
        return roomsMap;
    }

    public String getBlockedDirection() {
        return blockedDirection;
    }

    public void setBlockedDirection(String blockedDirection) {
        this.blockedDirection = blockedDirection;
    }

    public String getBlockedCondition() {
        return blockedCondition;
    }

    public void setBlockedCondition(String blockedCondition) {
        this.blockedCondition = blockedCondition;
    }

    public String getConditionItem() {
        return conditionItem;
    }

    public void setConditionItem(String conditionItem) {
        this.conditionItem = conditionItem;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomID=" + roomID +
                ", description='" + description + '\'' +
                ", beenVisited=" + beenVisited +
                ", roomsMap=" + roomsMap +
                ", blockedDirection='" + blockedDirection + '\'' +
                ", blockedCondition='" + blockedCondition + '\'' +
                ", conditionItem='" + conditionItem + '\'' +
                '}';
    }

    public Room(int roomID, String description, HashMap<String, Integer> roomsMap, String blockedDirection, String blockedCondition, String conditionItem) {
        this.roomID = roomID;
        this.description = description;
        this.roomsMap = roomsMap;
        this.blockedDirection = blockedDirection;
        this.blockedCondition = blockedCondition;
        this.conditionItem = conditionItem;
    }

    public Room navigate(String direction, Room currentRoom,ArrayList<Room> roomList,ArrayList<Item> playerItems) {
        /*for (Item item : playerItems) {
            if (item.getItemName().equalsIgnoreCase(currentRoom.conditionItem)){
                currentRoom.setBlockedDirection("");
                currentRoom.setConditionItem("");
            }
        }*/
        if (currentRoom.getBlockedDirection().toUpperCase().contains(direction.toUpperCase())){
            System.out.println("There is something blocking that direction, perhaps there are monsters or puzzles in the room to deal with?");
        }

        else {
            if (direction.equals("NORTH") || direction.equals("N")) {
                if (currentRoom.getRoomsMap().containsKey("North")) {
                    currentRoom = roomList.get(currentRoom.getRoomsMap().get("North") - 1);
                    System.out.println(currentRoom.getDescription());

                    return currentRoom;
                }
            }
            if (direction.equals("SOUTH") || direction.equals("S")) {
                if (currentRoom.getRoomsMap().containsKey("South")) {
                    currentRoom = roomList.get(currentRoom.getRoomsMap().get("South") - 1);
                    System.out.println(currentRoom.getDescription());
                    return currentRoom;

                }
            }
            if (direction.equals("EAST") || direction.equals("E")) {
                if (currentRoom.getRoomsMap().containsKey("East")) {
                    currentRoom = roomList.get(currentRoom.getRoomsMap().get("East")-1);
                    System.out.println(currentRoom.getDescription());
                    return currentRoom;

                }
            }
            if (direction.equals("WEST") || direction.equals("W")) {
                if (currentRoom.getRoomsMap().containsKey("West")) {
                    currentRoom = roomList.get(currentRoom.getRoomsMap().get("West")-1);
                    System.out.println(currentRoom.getDescription());
                    return currentRoom;

                }
            } else {
                System.out.println("You cannot move in that direction from this room.");
            }
            return currentRoom;
        }
        return currentRoom;
    }

    public static void explore(int roomID, ArrayList<Item> itemList, ArrayList<Monster> monsterList, ArrayList<Puzzle> puzzleList) {
        ArrayList<String> roomItems = new ArrayList<>();
        for (Item item : itemList) {
            if (item.getItemRoom() == roomID && !item.isRestrictedItem()) {
                roomItems.add(item.getItemName());
            }
        }
        if (roomItems.size()>0){
            System.out.println("This room contains these items:");
            for (String room : roomItems){
                System.out.println(room + "\n");
            }
        } else {
            System.out.println("This room contains no items right now.");
        }
        for (Puzzle puzzle : puzzleList) {
            if (puzzle.getPuzzleRoom()==roomID){
                System.out.println("There is a puzzle in this room.");
                break;
            }
        }
        for (Monster monster : monsterList) {
            if (monster.getMonsterRoom() == roomID) {
                System.out.println("There is a monster in this room.");
                break;
            }
        }
    }
}
