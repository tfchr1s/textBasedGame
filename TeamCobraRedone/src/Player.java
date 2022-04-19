import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player extends Person implements Serializable {
    private List<Item> playerItems;
    private List<Item> equippedItems = new ArrayList<>();
    private int playerLocation = 0;

    public Player() {

    }

    public static String ignore(Player player, ArrayList<Monster> monsterList, int roomID, Room room){
        Monster combatMonster = new Monster();
        boolean monsterPresent = false;
        for (Monster monster : monsterList) {
            if (monster.getMonsterRoom() == roomID) {
                combatMonster = monster;
                monsterPresent = true;
                break;
            }
        }
        if (monsterPresent) {
            combatMonster.setMonsterRoom(-1);
        } return "";
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerItems=" + playerItems +
                ", equippedItems=" + equippedItems +
                ", playerLocation=" + playerLocation +
                '}';
    }

    public static String attack(Player player, ArrayList<Monster> monsterList, int roomID, Room room){
        Monster combatMonster = new Monster();
        boolean monsterPresent = false;
        for (Monster monster : monsterList) {
            if (monster.getMonsterRoom() == roomID) {
                combatMonster = monster;
                monsterPresent = true;
                break;
            }
        }
        if (monsterPresent){
            if (playerSName(player.getName())){
                System.out.println(player.getName()+ "' stats: " + "\nHealth: " + player.getHealth() + "Attack: " + player.getAttack() + "\nMonster Health: " + combatMonster.getHealth() +  " Monster Attack: " + combatMonster.getAttack() + "\nYou can attack or escape.");
            } else {
                System.out.println(player.getName()+ "'s stats: " + "\nHealth: " + player.getHealth() + "Attack: " + player.getAttack() + "\nMonster Health: " + combatMonster.getHealth() +  " Monster Attack: " + combatMonster.getAttack() + "\nYou can attack or escape.");
            }
            while (player.getHealth() > 0 && combatMonster.getHealth() > 0) {
                Scanner sc = new Scanner(System.in);
                String input = "";
                input = sc.nextLine();

                if (input.equalsIgnoreCase("attack")) {
                    combatMonster.setHealth(combatMonster.getHealth()-player.getAttack());
                    player.setHealth(player.getHealth() - combatMonster.getAttack());
                    if (combatMonster.getHealth() > 0) {
                        if (playerSName(player.getName())){
                            System.out.println(player.getName()+ "' stats: " + "\nHealth: " + player.getHealth() + "Attack: " + player.getAttack() + "\nMonster Health: " + combatMonster.getHealth() +  " Monster Attack: " + combatMonster.getAttack() + "\nYou can attack or flee.");
                        } else {
                            System.out.println(player.getName()+ "'s stats: " + "\nHealth: " + player.getHealth() + "Attack: " + player.getAttack() + "\nMonster Health: " + combatMonster.getHealth() +  " Monster Attack: " + combatMonster.getAttack() + "\nYou can attack or flee.");
                        }
                    }
                }
                if (input.equalsIgnoreCase("Escape")) {
                    System.out.println("You have escaped from the monster, but it is still there...");
                    return "EXITCOMBAT";
                }
                if (player.getHealth() <= 0) {
                    System.out.println("You have died, type 'exit' to exit the game, or type 'new game' to start a new game");
                    input = sc.nextLine();
                    if (input.equalsIgnoreCase("exit")) {
                        System.exit(0);
                    }
                    if (input.equalsIgnoreCase("new game")) {
                        return "restart";

                    }
                }
                if (combatMonster.getHealth()<=0) {
                    System.out.println("You have defeated the monster!");
                    combatMonster.setMonsterRoom(-1);
                    if (room.getBlockedCondition().toUpperCase().contains("MONSTER")) {
                        System.out.println("The way to the " + room.getBlockedDirection()+ " is now open!");
                        room.setBlockedDirection("");
                    }
                    return "EXITCOMBAT";
                }
        }
        } return "EXITCOMBAT";
    }
    public static boolean playerSName(String name){
        if(name.substring(name.length() - 1).equalsIgnoreCase("s")){
            return true;
        } else return false;

    }
    public static void stats(Player player){
        if (playerSName(player.getName())) {
            System.out.println(player.getName() +"' stats\nHealth: " + player.getHealth() +"\nAttack: " + player.getAttack());
        } else {
            System.out.println(player.getName() +"'s stats\nHealth: " + player.getHealth() +"\nAttack: " + player.getAttack());

        }
    }

    public void checkInventory(Player player, ArrayList<Item> playerItems) {
        if (playerItems.size() > 0) {
            if (player.getName().substring(player.getName().length() - 1).equalsIgnoreCase("s")) {
                System.out.println(player.getName() + "' Inventory:\nEquippables:");
            } else {
                System.out.println(player.getName() + "'s Inventory:\nEquippables:");
            }
            for (Item item : playerItems) {
                if (item.getAttackBuff() > 0 || item.getHealthBuff() > 0) {
                    System.out.println(item.getItemName());
                }
            }
            if (player.getEquippedItems().size()>0){
                System.out.println("Equipped Items:");
            }
            for (Item item : player.getEquippedItems()) {
                System.out.println(item.getItemName());
            }
            System.out.println("Other Items:");
            for (Item item : playerItems) {
                if (item.getAttackBuff() == 0 && item.getHealthBuff() == 0) {
                    System.out.println(item.getItemName());
                }
            }
        } else {
            System.out.println("Your inventory is empty.");
        }
    }

    public Player(String name, int health, int attack, ArrayList<Item> playerItems) {
        super(name, health, attack);
        this.playerItems = playerItems;
    }

    public List<Item> getPlayerItems() {
        return playerItems;
    }

    public void setPlayerItems(ArrayList<Item> playerItems) {
        this.playerItems = playerItems;
    }

    public List<Item> getEquippedItems() {
        return equippedItems;
    }

    public void setEquippedItems(ArrayList<Item> equippedItems) {
        this.equippedItems = equippedItems;
    }

    public int getPlayerLocation() {
        return playerLocation;
    }

    public void setPlayerLocation(int playerLocation) {
        this.playerLocation = playerLocation;
    }
}

