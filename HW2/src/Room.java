/**
 * Represents a room in a game.
 */
public class Room {
    private String nameOfRoom;
    private int numberOfItems;
    private boolean statusOfPuzzle;
    private Item[] items;
    private Room[] connectionsWithOtherRooms;

    /**
     * Constructor for the Room class.
     *
     * @param nameOfRoom The name of the room.
     */
    public Room(String nameOfRoom) {
        this.nameOfRoom = nameOfRoom;
        this.numberOfItems = 0;
        this.statusOfPuzzle = false;
        this.connectionsWithOtherRooms = new Room[4];
        this.items = new Item[2];
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
     * Gets the number of items in the room.
     *
     * @return The number of items in the room.
     */
    public int getNumberOfItems() {
        return numberOfItems;
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
     * Gets an array of rooms connected to the current room.
     *
     * @return An array of connected rooms.
     */
    public Room[] getConnectionsWithOtherRooms() {
        return connectionsWithOtherRooms;
    }

    /**
     * Sets a connection with another room.
     *
     * @param connectedWith The index of the connection.
     * @param room The room to be connected.
     */
    public void setConnectionWithOtherRooms(int connectedWith ,Room room) {
        this.connectionsWithOtherRooms[connectedWith] = room;
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
     * @param item The item to be set.
     * @param index The index where the item should be set.
     */
    public void setItem(Item item, int index){
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

    /**
     * Cleans the room by removing all items and resetting the puzzle status.
     */
    public void cleanRoom(){
        this.setItem(null, 0);
        this.setItem(null, 1);
        this.setStatusOfPuzzle(false);
    }
}

