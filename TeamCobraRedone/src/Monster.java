import java.io.Serializable;
import java.util.ArrayList;

public class Monster extends Person implements Serializable {
    private int monsterID;
    private String description;
    private int monsterRoom;

    public static void examine(ArrayList<Monster> monsterList, int roomID){
        for (Monster monster : monsterList) {
            if (monster.getMonsterRoom() == roomID) {
                System.out.println("Monster Information:\nMonster Name: " + monster.getName()+ "\nMonster Attack: "+monster.getAttack() + "\nMonster Health: " + monster.getHealth()+"\n"+monster.getDescription());
            }
        }
    }
    public Monster(int monsterID,String name, int health, int attack, String description, int monsterRoom) {
        super(name,health,attack);
        this.monsterID = monsterID;
        this.description = description;
        this.monsterRoom = monsterRoom;
    }
    public Monster(){

    }

    public int getMonsterID() {
        return monsterID;
    }

    public void setMonsterID(int monsterID) {
        this.monsterID = monsterID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMonsterRoom() {
        return monsterRoom;
    }

    public void setMonsterRoom(int monsterRoom) {
        this.monsterRoom = monsterRoom;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "monsterID=" + monsterID +
                ", description='" + description + '\'' +
                ", monsterRoom=" + monsterRoom +
                '}';
    }
}


