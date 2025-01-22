/**
 * The Player class represents a player in a game with a name, inventory, and current room.
 */
public class Player {
    private String nameOfPlayer;
    private Bag bag;
    private Room currentRoom;

    /**
     * Constructor for creating a Player instance.
     *
     * @param nameOfPlayer The name of the player.
     * @param sizeOfBag The size of the player's inventory bag.
     */
    public Player(String nameOfPlayer, int sizeOfBag) {
        this.nameOfPlayer = nameOfPlayer;
        this.bag = new Bag("Starting bag",1, sizeOfBag);
        this.currentRoom = null;
    }

    /**
     * Gets the index of an item in the current room's item array.
     *
     * @param item The item to find in the room.
     * @return The index of the item in the room, or -1 if not found.
     */
    public int indexOfItemInRoom (Item item){
        if (this.currentRoom.getItems()[0] != null && this.currentRoom.getItems()[0].equals(item)) {
            return 0;
        } else if (this.currentRoom.getItems()[1] != null && this.currentRoom.getItems()[1].equals(item)) {
            return 1;
        } return -1;
    }

    /**
     * Finds the index of the specified item in the inventory of the associated Bag.
     *
     * @param item The item to find in the bag's inventory.
     * @return The index of the item in the bag's inventory, or -1 if the item is not present.
     */
    public int indexOfItemInBag (Item item) {
        for (int i = 0; i < bag.inventory.length; i++) {
            if (bag.inventory[i] != null && bag.inventory[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Picks up an item from the current room and adds it to the player's inventory.
     *
     * @param item The item to be picked up.
     */
    public void pickUpItem(Item item) {
        boolean tryingToAssignBag = item instanceof Bag;
        boolean legalAssignment = (this.getBag() instanceof LargeBag) && !(item instanceof LargeBag);
        //boolean legalAssignment = (!(this.getBag() instanceof LargeBag && item instanceof LargeBag) && !(this.getBag() instanceof LargeBag && item instanceof Bag) && !(this.getBag() instanceof Bag && item instanceof Bag));
        boolean itemIsinRoom = ((this.currentRoom.getItems()[0] != null && this.currentRoom.getItems()[0].equals(item)) || (this.currentRoom.getItems()[1] != null && this.currentRoom.getItems()[1].equals(item)));
        if (this.getBag().getSizeOfBag() == 0) {
            System.out.println(this.nameOfPlayer + "'s inventory is full.");
        } else {
            if (itemIsinRoom && tryingToAssignBag && !legalAssignment) {
                System.out.println(item.getNameOfItem() + " is not valid for storing.");
            } else {
                for (int i = 0; i < this.getBag().inventory.length; i++) {
                    if (this.getBag().inventory[i] == null) {
                        if (itemIsinRoom) {
                            this.getBag().inventory[i] = item;
                            System.out.println(this.nameOfPlayer + " picked up " + item.getNameOfItem() + " from " + this.currentRoom.getNameOfRoom() + ".");
                            this.currentRoom.setItem(null, indexOfItemInRoom(item));
                            break;
                        } else {
                            System.out.println(item.getNameOfItem() + " is not in " + this.currentRoom.getNameOfRoom() + ".");
                            break;
                        }
                    }
                    if (i == this.getBag().getSizeOfBag() - 1 && this.getBag().inventory[i] != null) {
                        System.out.println(this.nameOfPlayer + "'s inventory is full.");
                    }
                }
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
        for (int i = 0; i < bag.inventory.length; i++) {
            if (bag.inventory[i] != null && bag.inventory[i].equals(item)) {
                itemInBag = true;
                if (this.getCurrentRoom().getItems()[0] == null) {
                    roomIsFull = false;
                    getCurrentRoom().setItem(item, 0);
                    System.out.println(this.getNameOfPlayer() + " dropped " + bag.inventory[i].getNameOfItem() + " in " + this.currentRoom.getNameOfRoom() + ".");
                    bag.inventory[i] = null;
                    break;
                } else if (this.getCurrentRoom().getItems()[1] == null) {
                    roomIsFull = false;
                    getCurrentRoom().setItem(item, 1);
                    System.out.println(this.getNameOfPlayer() + " dropped " + bag.inventory[i].getNameOfItem() + " in " + this.currentRoom.getNameOfRoom() + ".");
                    bag.inventory[i] = null;
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
        if (this.currentRoom.getItems()[0] != null && this.currentRoom.getItems()[0].equals(item)) {
            System.out.println(this.nameOfPlayer + " disassembled " + this.currentRoom.getItems()[0].getNameOfItem() + ".");
            this.currentRoom.setItem(null, 0);
            wasDissasembled = true;
        } else if (this.currentRoom.getItems()[1] != null && this.currentRoom.getItems()[1].equals(item)) {
            System.out.println(this.nameOfPlayer + " disassembled " + this.currentRoom.getItems()[1].getNameOfItem() + ".");
            this.currentRoom.setItem(null, 1);
            wasDissasembled = true;
        } else if (!wasDissasembled) {
            for (int i = 0; i < bag.inventory.length; i++) {
                if (bag.inventory[i] != null && bag.inventory[i].equals(item)) {
                    System.out.println(this.nameOfPlayer + " disassembled " + bag.inventory[i].getNameOfItem() + ".");
                    bag.inventory[i] = null;
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
        for (int i = 0; i < this.bag.inventory.length; i++) {
            bag.inventory[i] = null;
        }
    }

    /**
     * Retrieves the player's bag.
     *
     * @return The player's bag.
     */

    public Bag getBag() {
        return bag;
    }

    /**
     * Sets the player's bag to a new bag instance.
     *
     * @param bag The new bag instance to set for the player.
     */
    public void setBag(Bag bag) {
        this.bag = bag;
    }

    /**
     * Compares this Player object with the specified object for equality.
     * Returns true if the given object is also a Player and its bag's value of items matches this player's bag's value of items.
     *
     * @param other The object to compare for equality.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof Player) && (((Player) other).getBag().valueOfItemsInBag() == this.getBag().valueOfItemsInBag());
    }

    /**
     * Returns a hash code value for the Player object. The hash code is based on the value of items in the player's bag.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return this.getBag().valueOfItemsInBag();
    }
}