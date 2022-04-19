import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GameDriver {
    public static ArrayList<Item> loadItems() {
        ArrayList<Item> itemList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("ItemsSave.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            itemList = (ArrayList<Item>) ois.readObject();
            ois.close();
            System.out.println("Items Loaded");
            return itemList;
        } catch (Exception e) {
            System.out.println("Items loading broke:" + e.getClass() + ":" + e.getMessage());
        }
        return itemList;
    }

    public static Player loadPlayer() {
        Player player = new Player();
        try {
            FileInputStream fis = new FileInputStream("PlayerSave.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            player = (Player) ois.readObject();
            ois.close();
            System.out.println("Player Loaded");
            return player;
        } catch (Exception e) {
            System.out.println("Player loading broke:" + e.getClass() + ":" + e.getMessage() + e.getCause());
        }
        return player;
    }

    public static ArrayList<Monster> loadMonsters() {
        ArrayList<Monster> monsterList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("MonstersSave.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            monsterList = (ArrayList<Monster>) ois.readObject();
            ois.close();
            System.out.println("Monsters Loaded");
            return monsterList;
        } catch (Exception e) {
            System.out.println("Monster loading broke:" + e.getClass() + ":" + e.getMessage());
        }
        return monsterList;
    }

    public static ArrayList<Puzzle> loadPuzzle() {
        ArrayList<Puzzle> puzzleList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("PuzzlesSave.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            puzzleList = (ArrayList<Puzzle>) ois.readObject();
            ois.close();
            System.out.println("Puzzles Loaded");
            return puzzleList;
        } catch (Exception e) {
            System.out.println("Puzzles loading broke:" + e.getClass() + ":" + e.getMessage());
        }
        return puzzleList;
    }

    public static ArrayList<Room> loadRooms() {
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("RoomsSave.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            roomList = (ArrayList<Room>) ois.readObject();
            ois.close();
            System.out.println("Rooms Loaded");
            return roomList;
        } catch (Exception e) {
            System.out.println("Rooms loading broke:" + e.getClass() + ":" + e.getMessage());
        }
        return roomList;
    }

    private static void savePuzzles(ArrayList<Puzzle> puzzleList) {
        try {
            FileOutputStream fos = new FileOutputStream("PuzzlesSave.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(puzzleList);
            oos.flush();
            oos.close();
            System.out.println("Puzzles Saved...");
        } catch (Exception e) {
            System.out.println("Puzzle Serialization broke:" + e.getClass() + ":" + e.getMessage());
        }
    }

    private static void saveItems(ArrayList<Item> itemList) {
        try {
            FileOutputStream fos = new FileOutputStream("ItemsSave.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(itemList);
            oos.flush();
            oos.close();
            System.out.println("Items Saved...");
        } catch (Exception e) {
            System.out.println("Items Serialization broke:" + e.getClass() + ":" + e.getMessage());
        }
    }

    private static void saveMonsters(ArrayList<Monster> monsterList) {
        try {
            FileOutputStream fos = new FileOutputStream("MonstersSave.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(monsterList);
            oos.flush();
            oos.close();
            System.out.println("Monsters Saved...");
        } catch (Exception e) {
            System.out.println("Monsters Serialization broke:" + e.getClass() + ":" + e.getMessage());
        }
    }

    private static void savePlayer(Player player) {
        try {
            FileOutputStream fos = new FileOutputStream("PlayerSave.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(player);
            oos.flush();
            oos.close();
            System.out.println("Player info Saved...");
        } catch (Exception e) {
            System.out.println("Player Serialization broke:" + e.getClass() + ":" + e.getMessage());
        }
    }

    public static void saveRooms(ArrayList<Room> roomList) {
        try {
            FileOutputStream fos = new FileOutputStream("RoomsSave.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(roomList);
            oos.flush();
            oos.close();
            System.out.println("Rooms Saved...");
        } catch (Exception e) {
            System.out.println("Rooms Serialization broke:" + e.getClass() + ":" + e.getMessage());
        }
    }

    //
    public static ArrayList<Puzzle> readPuzzleFile() throws FileNotFoundException {
        File file = new File("Puzzles.txt");
        Scanner sc = new Scanner(file);
        ArrayList<Puzzle> puzzleList = new ArrayList<>();
        while (sc.hasNextLine()) {
            int puzzleID = Integer.parseInt(sc.nextLine());
            String name = sc.nextLine();
            String description = sc.nextLine();
            String textSolution = sc.nextLine();
            String itemNeeded = sc.nextLine();
            int puzzleRoom = Integer.parseInt(sc.nextLine());
            int puzzleAttempts = Integer.parseInt(sc.nextLine());
            String solutionMessage = sc.nextLine();
            String failedMessage = sc.nextLine();
            Puzzle puzzle = new Puzzle(puzzleID, name, description, textSolution, itemNeeded, puzzleRoom, puzzleAttempts, solutionMessage, failedMessage);
            puzzleList.add(puzzle);
        }
        return puzzleList;
    }

    //
    public static ArrayList<Room> readRoomFile() throws FileNotFoundException {
        File file = new File("Rooms.txt");
        Scanner sc = new Scanner(file);
        ArrayList<Room> roomList = new ArrayList<>();
        while (sc.hasNextLine()) {
            int roomID = Integer.parseInt(sc.nextLine());
            String description = sc.nextLine();
            HashMap map = new HashMap();
            String key = sc.nextLine();
            while (!key.equals("----")) { //Nested loop that adds input key,value pairs to the map HashMap.
                int location = Integer.parseInt(sc.nextLine());
                map.put(key, location);
                key = sc.nextLine();
            }
            String blockedDirection = sc.nextLine();
            String blockedCondition = sc.nextLine();
            String conditionItem = sc.nextLine();
            Room room = new Room(roomID, description, map, blockedDirection, blockedCondition, conditionItem);
            roomList.add(room);
        }
        sc.close();
        return roomList;
    }

    //
    public static ArrayList<Monster> readMonsterFile() throws FileNotFoundException {
        File file = new File("Monsters.txt");
        Scanner sc = new Scanner(file);
        ArrayList<Monster> monsterList = new ArrayList<>();
        while (sc.hasNextLine()) {
            int monsterID = Integer.parseInt(sc.nextLine());
            String name = sc.nextLine();
            String description = sc.nextLine();
            int attack = Integer.parseInt(sc.nextLine());
            int health = Integer.parseInt(sc.nextLine());
            int monsterRoom = Integer.parseInt(sc.nextLine());
            Monster monster = new Monster(monsterID, name, health, attack, description, monsterRoom);
            monsterList.add(monster);
        }
        return monsterList;
    }

    //
    public static ArrayList<Item> readItemFile() throws FileNotFoundException {
        File file = new File("Items.txt");
        Scanner sc = new Scanner(file);
        ArrayList<Item> itemList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String itemName = sc.nextLine();
            String description = sc.nextLine();
            int itemRoom = Integer.parseInt(sc.nextLine());
            int attackBuff = Integer.parseInt(sc.nextLine());
            int healthBuff = Integer.parseInt(sc.nextLine());
            boolean restrictedItem = Boolean.parseBoolean(sc.nextLine());
            Item item = new Item(itemName, description, itemRoom, attackBuff, healthBuff, restrictedItem);
            itemList.add(item);
        }
        return itemList;
    }

    //Prints game instructions - Chris Hernandez
    public static void printHelp() {
        System.out.println("Help/Instructions:");
        System.out.println("Navigation controls:\nDirections: North - South - East - West");
        System.out.println("Explore - gets the directions of the room\n");
        System.out.println("Puzzle Commands:\nSolve - attempts to solve the puzzle in current room\n");
        System.out.println("Escape - escapes the current puzzle");
        System.out.println("Item commands:\nPick up <item> - picks up item that is in current room");
        System.out.println("Drop <item> - drops item into current room\nInspect<item> - Gets item description");
        System.out.println("Inventory - checks all items in current inventory\n");
        System.out.println("Monster/Combat commands: \nAttack - attacks the monster in battle");
        System.out.println("Escape: Leaves combat from current monster\nStats - checks player's current status");
    }

    public static void main(String[] args) throws FileNotFoundException {
        String input = "";
        //Gameplay loop.
        while (!input.equals("exit")) {
            int currentRoomID = 11;
            Room currentRoom;
            Scanner sc = new Scanner(System.in);
            //Initialization for all text file ArrayLists
            ArrayList<Item> itemList = readItemFile();
            ArrayList<Item> playerItemList = new ArrayList<>();
            ArrayList<Puzzle> puzzleList = readPuzzleFile();
            ArrayList<Room> roomList = readRoomFile();
            ArrayList<Monster> monsterList = readMonsterFile();
            currentRoom = roomList.get(currentRoomID - 1);
            System.out.println("Welcome to the Slip-Through Slammer Text Game!\nWhat would you like to name your character?");
            String name = sc.nextLine();
            Player player = new Player(name, 50, 10, playerItemList);
            ArrayList<Player> playerList = new ArrayList<>();
            playerList.add(player);
            System.out.println("For a list of commands, you can type 'help'.");
            System.out.println(currentRoom.getDescription());
            input = "placeholder";
            while (!input.equals("restart")) {
                input = sc.nextLine().toUpperCase().strip();
                if (input.contains("PICK UP")) {
                    Item.pickUp(input, itemList, player, currentRoomID);
                } else if (input.contains("DROP")) {
                    Item.drop(input, itemList, playerItemList, currentRoomID);
                } else if (input.contains("EQUIP") && !input.contains("UNEQUIP")) {
                    Item.equip(input, player);
                } else if (input.contains("UNEQUIP")) {
                    Item.unEquip(input, player);
                } else if (input.contains("INSPECT")) {
                    Item.inspect(input, playerItemList, currentRoomID);
                } else {
                    switch (input) {
                        case "NORTH":
                        case "N":
                        case "SOUTH":
                        case "S":
                        case "EAST":
                        case "E":
                        case "WEST":
                        case "W":
                            currentRoom = currentRoom.navigate(input, currentRoom, roomList, (ArrayList<Item>) player.getPlayerItems());
                            currentRoomID = currentRoom.getRoomID();
                            break;
                        case "EXPLORE":
                            currentRoom.explore(currentRoomID, itemList, monsterList, puzzleList);
                            break;
                        case "SOLVE":
                            Puzzle.solve(puzzleList, currentRoomID, player, itemList, currentRoom);
                            break;
                        case "INVENTORY":
                        case "I":
                            player.checkInventory(player, (ArrayList<Item>) player.getPlayerItems());
                            break;
                        case "STATS":
                            Player.stats(player);
                            break;
                        case "EXAMINE MONSTER":
                            Monster.examine(monsterList, currentRoomID);
                            break;
                        case "ATTACK":
                            input = Player.attack(player, monsterList, currentRoomID, currentRoom);
                            break;
                        case "IGNORE":
                            Player.ignore(player, monsterList, currentRoomID, currentRoom);
                            break;
                        case "EXITCOMBAT":
                            break;
                        case "HELP":
                        case "H":
                        case "INSTRUCTIONS":
                            printHelp();
                            break;
                        case "EXIT":
                            System.exit(0);
                            break;
                        case "RESTART":
                            input = "restart";
                            break;
                        case "SAVE":
                            //Save Doesn't seem to work, and I don't know enough to fix it.
                            System.out.println(currentRoomID);
                            player.setPlayerLocation(currentRoomID);
                            System.out.println(player.getName());
                            player.setName(player.getName());
                            saveRooms(roomList);
                            savePlayer(player);
                            saveMonsters(monsterList);
                            saveItems(itemList);
                            savePuzzles(puzzleList);
                            break;
                        case "LOAD":
                            //Load doesnt work, and I dont know enough to fix it.
                            roomList = loadRooms();
                            itemList = loadItems();
                            monsterList = loadMonsters();
                            player = loadPlayer();
                            player.setName(name);
                            puzzleList = loadPuzzle();
                            currentRoomID = player.getPlayerLocation();
                            currentRoom = roomList.get(currentRoomID - 1);
                            System.out.println(currentRoom.getDescription());
                            break;
                        default:
                            System.out.println("Unknown command, type 'help' for a list of commands");
                            break;
                    }
                }
            }
        }
    }


}
