import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Puzzle implements Serializable {
    private int puzzleID;
    private String name;
    private String description;
    private String textSolution;
    private String itemSolution;
    private int puzzleRoom;
    private String solutionMessage;
    private String failedMessage;
    private int puzzleAttempts;

    public Puzzle(int puzzleID, String name, String description, String textSolution, String itemSolution, int puzzleRoom, int puzzleAttempts, String solutionMessage, String failedMessage) {
        this.puzzleID = puzzleID;
        this.name = name;
        this.description = description;
        this.textSolution = textSolution;
        this.itemSolution = itemSolution;
        this.puzzleRoom = puzzleRoom;
        this.puzzleAttempts = puzzleAttempts;
        this.solutionMessage = solutionMessage;
        this.failedMessage = failedMessage;

    }

    //
    public static void solve(ArrayList<Puzzle> puzzleList, int currentRoomID, Player player, ArrayList<Item> itemList, Room currentRoom) {
        Scanner sc = new Scanner(System.in);
        String input = "";
        Puzzle currentPuzzle = new Puzzle();
        for (Puzzle puzzle : puzzleList) {
            if (puzzle.getPuzzleRoom() == currentRoomID) {
                currentPuzzle = puzzle;
            }
        }
        System.out.println(currentPuzzle.getDescription());
        int attempts = currentPuzzle.puzzleAttempts;
        while (attempts >= 0) {
            input = sc.nextLine().toUpperCase().strip();
            if (input.equalsIgnoreCase("Escape")) {
                System.out.println("You have stopped trying to solve the puzzle.");
                break;
            }
            if (input.toUpperCase().contains(currentPuzzle.textSolution)) {
                System.out.println(currentPuzzle.solutionMessage);
                currentPuzzle.setPuzzleRoom(-1);
                for (Item item : itemList) {
                    if (item.isRestrictedItem() && item.getItemRoom() == currentRoomID) {
                        item.setRestrictedItem(false);
                    }
                }
                break;
            }
            if (input.toUpperCase().contains("USE")) {
                String tempInput = input.toUpperCase();
                tempInput = tempInput.replaceAll("USE ", "");
                String inputItem = tempInput.strip();
                for (Item item : player.getPlayerItems()) {
                    if (inputItem.equalsIgnoreCase(item.getItemName()) && currentPuzzle.getItemSolution().equalsIgnoreCase(inputItem)) {
                        System.out.println(currentPuzzle.solutionMessage);
                        currentPuzzle.setPuzzleRoom(-1);
                        attempts = -1;
                        for (Item item1 : itemList) {
                            if (item1.isRestrictedItem() && item1.getItemRoom() == currentRoomID) {
                                item1.setRestrictedItem(false);
                            }
                        }
                        if (currentRoom.getBlockedCondition().toUpperCase().contains("PUZZLE")) {
                            System.out.println("The way to the " + currentRoom.getBlockedDirection() + " is now open!");
                            currentRoom.setBlockedDirection("");
                        }
                        break;
                    }

                }

            } else {
                System.out.println("That is not the right solution...");
                attempts = attempts - 1;
                if (attempts == 0) {
                    System.out.println(currentPuzzle.failedMessage);
                    break;
                }
            }
        }
    }


    public Puzzle() {
    }

    public String getItemSolution() {
        return itemSolution;
    }

    public String getDescription() {
        return description;
    }

    public int getPuzzleRoom() {
        return puzzleRoom;
    }

    public void setPuzzleRoom(int puzzleRoom) {
        this.puzzleRoom = puzzleRoom;
    }

    public int getPuzzleAttempts() {
        return puzzleAttempts;
    }

    public void setPuzzleAttempts(int puzzleAttempts) {
        this.puzzleAttempts = puzzleAttempts;
    }

    public String getSolutionMessage() {
        return solutionMessage;
    }

    public void setSolutionMessage(String solutionMessage) {
        this.solutionMessage = solutionMessage;
    }

    public String getFailedMessage() {
        return failedMessage;
    }

    public void setFailedMessage(String failedMessage) {
        this.failedMessage = failedMessage;
    }
}
