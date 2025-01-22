/**
 * Represents a room in a game.
 */
public class Room implements Cloneable {
    private String nameOfRoom;
    private boolean statusOfPuzzle;
    private Item[] items;
    private boolean keyUsed;
    private Key placeForKey;
    static final int ITEMS_IN_ROOM = 2;

    /**
     * Constructor for the Room class.
     *
     * @param nameOfRoom The name of the room.
     */
    public Room(String nameOfRoom) {
        this.nameOfRoom = nameOfRoom;
        this.statusOfPuzzle = false;
        this.items = new Item[ITEMS_IN_ROOM];
        this.keyUsed = false;
        this.placeForKey = null;
    }

    /**
     * Adds an item to the room.
     *
     * @param item The item to be added.
     */
    public void addItemToRoom(Item item) {
        if (this.items[0] == null) {
            this.items[0] = item;
            System.out.println(item.getNameOfItem() + " was added to the game.");
        } else if (this.items[1] == null) {
            this.items[1] = item;
            System.out.println(item.getNameOfItem() + " was added to the game.");
        } else {
            System.out.println("Could not add " + item.getNameOfItem() + " to the game.");
        }
    }

    /**
     * Checks the status of the puzzle in the room.
     *
     * @return The status of the puzzle.
     */
    public boolean isStatusOfPuzzle() {
        return statusOfPuzzle;
    }

    /**
     * Sets the status of the puzzle in the room.
     *
     * @param statusOfPuzzle The new status of the puzzle.
     */
    public void setStatusOfPuzzle(boolean statusOfPuzzle) {
        this.statusOfPuzzle = statusOfPuzzle;
    }

    /**
     * Gets an array of items in the room.
     *
     * @return An array of items in the room.
     */
    public Item[] getItems() {
        return items;
    }

    /**
     * Sets an item in the specified index in the room.
     *
     * @param item  The item to be set.
     * @param index The index where the item should be set.
     */
    public void setItem(Item item, int index) {
        this.items[index] = item;
    }

    /**
     * Gets the name of the room.
     *
     * @return The name of the room.
     */
    public String getNameOfRoom() {
        return nameOfRoom;
    }

    public boolean isKeyUsed() {
        return keyUsed;
    }

    public void setKeyUsed(boolean keyUsed) {
        this.keyUsed = keyUsed;
    }

    public void setPlaceForKey(Key placeForKey) {
        this.placeForKey = placeForKey;
    }

    public Key getPlaceForKey() {
        return placeForKey;
    }

    /**
     * Cleans the room by removing all items and resetting the puzzle status, and the usage of the key.
     */
    public void cleanRoom() {
        this.setItem(null, 0);
        this.setItem(null, 1);
        this.setStatusOfPuzzle(false);
        this.setPlaceForKey(null);
        this.setKeyUsed(false);
    }

    /**
     * Calculates the total value of items in the room.
     *
     * @return The total value of items in the room.
     */
    public int valueOfItemsInRoom() {
        int valueOfItemsInRoom = 0;
        if (items[0] != null) {
            valueOfItemsInRoom += items[0].getValue();
        }
        if (items[1] != null) {
            valueOfItemsInRoom += items[1].getValue();
        }
        return valueOfItemsInRoom;
    }

    /**
     * Compares this Room object with the specified object for equality.
     * Returns true if the given object is also a Room and its attributes match with this room's attributes.
     *
     * @param other The object to compare for equality.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Room)) {
            return false;
        } else {
            return ((Room) other).nameOfRoom.equals(this.nameOfRoom) && ((Room) other).isKeyUsed() == this.isKeyUsed() && ((Room) other).isStatusOfPuzzle() == this.isStatusOfPuzzle() && ((Room) other).valueOfItemsInRoom() == this.valueOfItemsInRoom();
        }
    }

    /**
     * Returns a hash code value for the Room object. The hash code is based on the room's name, key usage, puzzle status, and total value of items.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = 0;
        if (this.isKeyUsed()) {
            result += 13;
        } else if (this.isStatusOfPuzzle()) {
            result += 387;
        }
        return (this.getNameOfRoom().hashCode() + this.valueOfItemsInRoom() + result);
    }

    /**
     * Represents a room in a game environment.
     */
    @Override
    public String toString(){
       return  "Room: " + this.nameOfRoom;
    }

    /**
     * Creates and returns a deep copy of the room.
     * @return a deep copy of the room
     */
    @Override
    public Room clone(){
        try {
            Room copy = (Room) super.clone();
            copy.nameOfRoom = this.nameOfRoom;
            if (this.getItems()[0] != null) {
                copy.setItem(this.getItems()[0].clone(), 0);
            }
            if (this.getItems()[1] != null) {
                copy.setItem(this.getItems()[1].clone(), 1);
            }

            if (this.placeForKey != null) {
                copy.placeForKey = this.placeForKey.clone();
            }
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}

