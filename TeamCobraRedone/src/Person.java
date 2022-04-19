import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {
    private String name;
    private int health;
    private int attack;
    public Person(){

    }
    public Person(String name, int health, int attack) {
        this.name = name;
        this.health = health;
        this.attack = attack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", attack=" + attack +
                '}';
    }
}

