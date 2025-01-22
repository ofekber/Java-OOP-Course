/**
 * The Player class represents a player in a game with a name, inventory, and current room.
 */
public class Player {
    private String nameOfPlayer;
    private Item[] bag;
    private Room currentRoom;

    /**
     * Constructor for creating a Player instance.
     *
     * @param nameOfPlayer The name of the player.
     * @param sizeOfBag The size of the player's inventory bag.
     */
    public Player(String nameOfPlayer, int sizeOfBag) {
        this.nameOfPlayer = nameOfPlayer;
        this.bag = new Item[sizeOfBag];
        this.currentRoom = null;
    }

    /**
     * Gets the index of an item in the current room's item array.
     *
     * @param item The item to find in the room.
     * @return The index of the item in the room, or -1 if not found.
     */
    public int indexOfItemInRoom (Item item){
        if (this.currentRoom.getItems()[0] == item) {
            return 0;
        } else if (this.currentRoom.getItems()[1] == item) {
            return 1;
        } return -1;
    }

    /**
     * Picks up an item from the current room and adds it to the player's inventory.
     *
     * @param item The item to be picked up.
     */
    public void pickUpitem(Item item) {
        boolean itemIsinRoom = ((this.currentRoom.getItems()[0] == item) || (this.currentRoom.getItems()[1] == item));
        for (int i = 0; i < bag.length; i++) {
            if (bag[i] == null) {
                if (itemIsinRoom) {
                    bag[i] = item;
                    System.out.println(this.nameOfPlayer + " picked up " + item.getNameOfItem() + " from " + this.currentRoom.getNameOfRoom() + ".");
                    this.currentRoom.setItem(null, indexOfItemInRoom(item));
                    break;
                } else {
                    System.out.println(item.getNameOfItem() + " is not in " + this.currentRoom.getNameOfRoom() + ".");
                    break;
                }
            }
            if (i == bag.length - 1 && bag[i] != null) {
                System.out.println(this.nameOfPlayer + "'s inventory is full.");
            }
        }
    }

    /**
     * Drops an item from the player's inventory into the current room.
     *
     * @param item The item to be dropped.
     */
    public void dropItem(Item item) {
        boolean itemInBag = false;
        boolean roomIsFull = true;
        for (int i = 0; i < bag.length; i++) {
            if (bag[i] == item) {
                itemInBag = true;
                if (this.getCurrentRoom().getItems()[0] == null) {
                    roomIsFull = false;
                    getCurrentRoom().setItem(item, 0);
                    bag[i] = null;
                    System.out.println(this.getNameOfPlayer() + " dropped " + item.getNameOfItem() + " in " + this.currentRoom.getNameOfRoom() + ".");
                    break;
                } else if (this.getCurrentRoom().getItems()[1] == null) {
                    roomIsFull = false;
                    getCurrentRoom().setItem(item, 1);
                    bag[i] = null;
                    System.out.println(this.getNameOfPlayer() + " dropped " + item.getNameOfItem() + " in " + this.currentRoom.getNameOfRoom() + ".");
                    break;
                }
            }
        }  if (!itemInBag) {
            System.out.println(item.getNameOfItem() + " is not in " + this.getNameOfPlayer() + "'s inventory.");
        } else if (roomIsFull) {
            System.out.println(this.currentRoom.getNameOfRoom() + " is full.");
        }
    }

    /**
     * Disassembles an item either from the current room or the player's inventory.
     *
     * @param item The item to be disassembled.
     */
    public void disassembleItem(Item item) {
        boolean wasDissasembled = false;
        if (this.currentRoom.getItems()[0] == item) {
            this.currentRoom.setItem(null, 0);
            System.out.println(this.nameOfPlayer + " disassembled " + item.getNameOfItem() + ".");
            wasDissasembled = true;
        } else if (this.currentRoom.getItems()[1] == item) {
            this.currentRoom.setItem(null, 1);
            System.out.println(this.nameOfPlayer + " disassembled " + item.getNameOfItem() + ".");
            wasDissasembled = true;
        } else if (!wasDissasembled) {
            for (int i = 0; i < bag.length; i++) {
                if (bag[i] == item) {
                    bag[i] = null;
                    System.out.println(this.nameOfPlayer + " disassembled " + item.getNameOfItem() + ".");
                    wasDissasembled = true;
                }
            }
        }
        if (!wasDissasembled){
            System.out.println(this.nameOfPlayer + " could not disassemble " + item.getNameOfItem() + ".");
        }
    }

    /**
     * Gets the current room of the player.
     *
     * @return The current room of the player.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Sets the current room of the player.
     *
     * @param currentRoom The new current room for the player.
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getNameOfPlayer() {
        return nameOfPlayer;
    }

    /**
     * Solves the puzzle in the current room if there is an active puzzle.
     */
    public void solvePuzzle() {
        if (!this.currentRoom.isStatusOfPuzzle()) {
            System.out.println("There is no active puzzle in " + this.currentRoom.getNameOfRoom()+".");
        } else {
            System.out.println(this.nameOfPlayer + " is solving the puzzle in " + this.currentRoom.getNameOfRoom()+".");
            this.getCurrentRoom().setStatusOfPuzzle(false);
        }
    }

    /**
     * Clears the player's inventory by setting all bag slots to null.
     */
    public void cleanBag(){
        for (int i = 0; i < this.bag.length; i++) {
            bag[i] = null;
        }
    }
}

