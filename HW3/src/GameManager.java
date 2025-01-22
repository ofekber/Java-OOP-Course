/**
 * The GameManager class manages the game's state, including players, rooms, items, and connections between rooms.
 */
public class GameManager {
    private Player player;
    private Room[] rooms;
    static final int NORTH = 0;
    static final int SOUTH = 1;
    static final int EAST = 2;
    static final int WEST = 3;
    static final int MAX_ROOMS = 5;
    static final int ALL_CONNECTIONS_WITH_OTHER_ROOMS = 4;

    /**
     * Constructs a new GameManager instance with default values.
     */
    public GameManager() {
        this.player = null;
        this.rooms = new Room[5];
    }

    /**
     * Adds a player to the game.
     *
     * @param player The player to be added.
     */
    public void addPlayer(Player player) {
        if (this.player == null) {
            this.player = player;
            System.out.println(player.getNameOfPlayer() + " was added to the game.");
        } else {
            System.out.println("Could not add " + player.getNameOfPlayer() + " to the game.");
        }
    }

    /**
     * Removes a player from the game.
     *
     * @param player The player to be removed.
     */
    public void removePlayer(Player player) {
        if (this.player == null) {
            System.out.println(player.getNameOfPlayer() + " does not exist.");
        } else {
            this.player.cleanBag();
            this.player.setCurrentRoom(null);
            this.player = null;
            System.out.println(player.getNameOfPlayer() + " was removed from the game.");
        }
    }

    /**
     * Adds a room to the game.
     *
     * @param room The room to be added.
     */
    public void addRoom(Room room) {
        if (isLegal(room)) {
            System.out.println("Could not add " + room.getNameOfRoom() + " to the game.");
        } else {
            for (int i = 0; i < MAX_ROOMS; i++) {
                if (this.rooms[i] == null) {
                    this.rooms[i] = room;
                    System.out.println(room.getNameOfRoom() + " was added to the game.");
                    break;
                }
                if (i == MAX_ROOMS - 1) {
                    System.out.println("Could not add " + room.getNameOfRoom() + " to the game.");
                }
            }
        }
    }

    /**
     * Removes a room from the game.
     *
     * @param room The room to be removed.
     */
    public void removeRoom(Room room) {
        if (!isLegal(room)) {
            System.out.println(room.getNameOfRoom() + " does not exist.");
        } else if (isLegal(room) && player != null && this.player.getCurrentRoom().equals(room)) {
            System.out.println(this.player.getCurrentRoom() + " could not be removed.");
        } else {
            removeConnection(equalRooms(room));
            room.cleanRoom();
            for (int i = 0; i < MAX_ROOMS; i++) {
                if (this.rooms[i].equals(room)) {
                    System.out.println(rooms[i].getNameOfRoom() + " was removed from the game.");
                    this.rooms[i] = null;
                    break;
                }
            }
        }
    }

    private Room equalRooms(Room room) {
        for (Room check : rooms) {
            if (check != null) {
                if (check.equals(room)) {
                    return check;
                }
            }
        } return room;
    }

    /**
     * Adds an item to a room in the game.
     *
     * @param room The room where the item will be added.
     * @param item The item to be added.
     */
    public void addItem(Room room, Item item) {
        boolean islegal = false;
        for (int i = 0; i < MAX_ROOMS; i++) {
            if (this.rooms[i] != null && this.rooms[i].equals(room) && rooms[i].getNumberOfItems() < 2) {
                rooms[i].addItemToRoom(item);
                islegal = true;
            }
        }
        if (!islegal) {
            System.out.println("Could not add " + item.getNameOfItem() + " to the game.");
        }
    }

    /**
     * Connects two rooms in the game.
     *
     * @param room1      The first room to be connected.
     * @param room2      The second room to be connected.
     * @param direction  The direction in which the rooms should be connected.
     */
    public void connectRooms(Room room1, Room room2, Direction direction) {
        int room1Connection = -1;
        int room2Connection = -1;

        switch (direction) {
            case NORTH:
                room1Connection = NORTH;
                room2Connection = SOUTH;
                break;
            case SOUTH:
                room1Connection = SOUTH;
                room2Connection = NORTH;
                break;
            case EAST:
                room1Connection = EAST;
                room2Connection = WEST;
                break;
            case WEST:
                room1Connection = WEST;
                room2Connection = EAST;
                break;
        }
        if (room1.getConnectionsWithOtherRooms()[room1Connection] != null || room2.getConnectionsWithOtherRooms()[room2Connection] != null) {
            System.out.println("Could not connect " + room1.getNameOfRoom() + " and " + room2.getNameOfRoom() + ".");
        } else {
            switch (direction) {
                case NORTH:
                    room1.setConnectionWithOtherRooms(NORTH, room2);
                    room2.setConnectionWithOtherRooms(SOUTH, room1);
                    break;
                case SOUTH:
                    room1.setConnectionWithOtherRooms(SOUTH, room2);
                    room2.setConnectionWithOtherRooms(NORTH, room1);
                    break;
                case EAST:
                    room1.setConnectionWithOtherRooms(EAST, room2);
                    room2.setConnectionWithOtherRooms(WEST, room1);
                    break;
                case WEST:
                    room1.setConnectionWithOtherRooms(WEST, room2);
                    room2.setConnectionWithOtherRooms(EAST, room1);
                    break;
            }
            System.out.println(room1.getNameOfRoom() + " and " + room2.getNameOfRoom() + " are connected.");
        }
    }

    /**
     * Sets the starting room for the player in the game.
     *
     * @param room The room where the player will start.
     */
    public void startPlayer(Room room) {
        if (this.player.getCurrentRoom() == null) {
            this.player.setCurrentRoom(room);
            System.out.println(this.player.getNameOfPlayer() + " starts in " + player.getCurrentRoom().getNameOfRoom() + ".");
        } else {
            System.out.println(this.player.getNameOfPlayer() + " has already started.");
        }
    }

    /**
     * Moves the player in a specified direction in the game.
     *
     * @param direction The direction in which the player should move.
     */
    public void movePlayer(Direction direction) {
        int converted = convertDirection(direction);
        if (this.player.getCurrentRoom().isStatusOfPuzzle() || this.player.getCurrentRoom().getConnectionsWithOtherRooms()[converted] == null) {
            System.out.println(this.player.getNameOfPlayer() + " could not move via the " + direction.toString().toLowerCase() + " exit.");
        } else {
            Room movedFrom = this.player.getCurrentRoom();
            this.player.setCurrentRoom(this.player.getCurrentRoom().getConnectionsWithOtherRooms()[converted]);
            System.out.println(this.player.getNameOfPlayer() + " moved from " + movedFrom.getNameOfRoom() + " to " + this.player.getCurrentRoom().getNameOfRoom() + " via the " + direction.toString().toLowerCase() + " exit.");
        }
    }

    /**
     * Picks up an item in the current room and adds it to the player's inventory.
     *
     * @param item The item to be picked up.
     */
    public void pickUpItem(Item item) {
        this.player.pickUpItem(item);
    }

    /**
     * Drops an item from the player's inventory to the current room.
     *
     * @param item The item to be dropped.
     */
    public void dropItem(Item item) {
        this.player.dropItem(item);

    }

    /**
     * Disassembles an item in the player's inventory.
     *
     * @param item The item to be disassembled.
     */
    public void disassembleItem(Item item) {
        if (this.player != null && this.player.getCurrentRoom() != null) {
            this.player.disassembleItem(item);
        }
        else {
            System.out.println("could not disassemble " + item.getNameOfItem() + ".");
        }
    }

    /**
     * Activates a puzzle in the specified room.
     *
     * @param room The room where the puzzle will be activated.
     */
    public void activatePuzzle(Room room) {
        if (room.isKeyUsed()) {
            System.out.println(room.getNameOfRoom() + " was unlocked with " + room.getPlaceForKey().getNameOfItem() + ".");
        } else {
            room.setStatusOfPuzzle(true);
        }
    }

    /**
     * Deactivates a puzzle in the specified room.
     *
     * @param room The room where the puzzle will be deactivated.
     */
    public void deactivatePuzzle(Room room) {
        if (room.isKeyUsed()) {
            System.out.println(room.getNameOfRoom() + " was unlocked with " + room.getPlaceForKey().getNameOfItem() + ".");
        } else {
            room.setStatusOfPuzzle(false);
        }
    }

    /**
     * Solves the puzzle in the player's current room.
     */
    public void solvePuzzle() {
        if (!(this.player.getCurrentRoom() == null)) {
            this.player.solvePuzzle();
        }
    }

    /**
     * Checks if a room is legal (exists in the game's rooms array).
     *
     * @param room The room to be checked.
     * @return True if the room is legal, false otherwise.
     */
    private boolean isLegal(Room room) {
        for (Room check : rooms) {
            if (check != null) {
                if (check.equals(room)) {return true;}
            }
        }
        return false;
    }

    /**
     * Converts a direction enum to an integer representation.
     *
     * @param direction The direction to be converted.
     * @return The integer representation of the direction.
     */
    private int convertDirection(Direction direction) {
        switch (direction) {
            case NORTH:
                return 0;
            case SOUTH:
                return 1;
            case EAST:
                return 2;
            case WEST:
                return 3;
        }
        return -1;
    }

    /**
     * Removes connections to a specified room from other connected rooms.
     *
     * @param room The room for which connections will be removed.
     */
    public void removeConnection(Room room) {
        Room[] connections = room.getConnectionsWithOtherRooms();
        for (int i = 0; i < ALL_CONNECTIONS_WITH_OTHER_ROOMS; i++) {
            if (connections[i] != null) {
                for (int j = 0; j < ALL_CONNECTIONS_WITH_OTHER_ROOMS; j++) {
                    if ((connections[i].getConnectionsWithOtherRooms()[j] != null) && (connections[i].getConnectionsWithOtherRooms()[j] == room)) {
                        connections[i].setConnectionWithOtherRooms(j, null);
                    }
                } room.getConnectionsWithOtherRooms()[i] = null;
            }
        }
    }

    /**
     * Uses the specified item, invoking its useItem method with the current player as the parameter.
     *
     * @param item The item to be used.
     */
    public void useItem(Item item){
        item.useItem(this.player);
    }

    public Player getPlayer() {
        return player;
    }
}
