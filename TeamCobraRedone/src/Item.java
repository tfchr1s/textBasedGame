import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

public class Item implements Serializable {
    private String itemName;
    private final String description;
    private int itemRoom;
    private int attackBuff;
    private int healthBuff;
    private boolean restrictedItem;

    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", itemRoom=" + itemRoom +
                ", attackBuff=" + attackBuff +
                ", healthBuff=" + healthBuff +
                ", restrictedItem=" + restrictedItem +
                '}';
    }

    public static void pickUp(String itemToPickUp, ArrayList<Item> itemList, Player player, int itemRoom) {
        String tempInput = itemToPickUp;
        tempInput = tempInput.replaceAll("PICK UP ", "");
        String itemToPickup = tempInput.strip();
        for (Item item : itemList) {
            if (item.getItemRoom() == itemRoom && item.getItemName().equalsIgnoreCase(itemToPickup)&& !item.isRestrictedItem()) {
                player.getPlayerItems().add(item);
                item.setItemRoom(-1);
                String itemPrint = item.getItemName().substring(0, 1).toUpperCase();
                String itemPrintCap = itemPrint + item.getItemName().substring(1);
                System.out.println(itemPrintCap + " has been picked up.");
                return;
            }
        }
        System.out.println("That input cannot be picked up.");
    }

    public static void drop(String itemToPickUp, ArrayList<Item> itemList, ArrayList<Item> playerItemList, int itemRoom) {
        String tempInput = itemToPickUp;
        tempInput = tempInput.replaceAll("DROP ", "");
        String itemToDrop = tempInput.strip();
        for (Item item : playerItemList) {
            if (item.getItemName().equalsIgnoreCase(itemToDrop)) {
                playerItemList.remove(item);
                String itemPrint = item.getItemName().substring(0, 1).toUpperCase();
                String itemPrintCap = itemPrint + item.getItemName().substring(1);
                System.out.println(itemPrintCap + " has been dropped.");
                break;
            }
        }
        for (Item item : itemList) {
            if (item.getItemName().equalsIgnoreCase(itemToDrop)) {
                item.setItemRoom(itemRoom);
            }
        }
    }

    //
    public static void equip(String itemNameToEquip, Player player) {
        String tempInput = itemNameToEquip;
        tempInput = tempInput.replaceAll("EQUIP ", "");
        String itemToEquip = tempInput.strip();
        for (Item item : player.getPlayerItems()) {
            if (item.getItemName().equalsIgnoreCase(itemToEquip)) {
                player.setHealth(player.getHealth() + item.getHealthBuff());
                player.setAttack(player.getAttack() + item.getAttackBuff());
                player.getEquippedItems().add(item);
                player.getPlayerItems().remove(item);
                System.out.println(item.getItemName() + " has been equipped.");
                break;
            }
        }
    }

    public static void unEquip(String itemToUnEquip, Player player) {
        String tempInput = itemToUnEquip;
        tempInput = tempInput.replaceAll("UNEQUIP ", "");
        String itemUnEquip = tempInput.strip();
        for (Item item : player.getEquippedItems()) {
            if (item.getItemName().equalsIgnoreCase(itemUnEquip)) {
                player.setHealth(player.getHealth() - item.getHealthBuff());
                player.setAttack(player.getAttack() - item.getAttackBuff());
                player.getPlayerItems().add(item);
                player.getEquippedItems().remove(item);
                System.out.println(item.getItemName() + " has been unequipped.");
                break;
            }
        }
    }

    public static void inspect(String itemToInspect, ArrayList<Item> playerItemList, int currentRoomID) {
        String tempInput = itemToInspect;
        tempInput = tempInput.replaceAll("INSPECT ", "");
        String itemInspect = tempInput.strip();
        for (Item item : playerItemList) {
            if (item.getItemName().equalsIgnoreCase(itemInspect)&& !item.isRestrictedItem()){
                System.out.println(item.description);
            }
        }
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void getItemDescription() {
        System.out.println(description);
    }

    public boolean isRestrictedItem() {
        return restrictedItem;
    }

    public void setRestrictedItem(boolean restrictedItem) {
        this.restrictedItem = restrictedItem;
    }

    public Item(String itemName, String description, int itemRoom, int attackBuff, int healthBuff, boolean restrictedItem) {
        this.itemName = itemName;
        this.description = description;
        this.itemRoom = itemRoom;
        this.attackBuff = attackBuff;
        this.healthBuff = healthBuff;
        this.restrictedItem = restrictedItem;
    }

    public String getDescription() {
        return description;
    }

    public int getItemRoom() {
        return itemRoom;
    }

    public void setItemRoom(int itemRoom) {
        this.itemRoom = itemRoom;
    }

    public int getItemAttack(String description) {
        String numbers = description.replaceAll("[^0-9]", "");
        int attackValue = Integer.parseInt(numbers);
        return attackValue;
    }

    public int getAttackBuff() {
        return attackBuff;
    }

    public void setAttackBuff(int attackBuff) {
        this.attackBuff = attackBuff;
    }

    public int getHealthBuff() {
        return healthBuff;
    }

    public void setHealthBuff(int healthBuff) {
        this.healthBuff = healthBuff;
    }
}
