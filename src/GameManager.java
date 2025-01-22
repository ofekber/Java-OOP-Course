public class GameManager implements Cloneable{
    private Player player;
    private QuartlyLinkedList<Room> rooms;

    public GameManager() {
        this.player = null;
        this.rooms = new QuartlyLinkedList<>();
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
     * Sets the starting room for the player in the game.
     *
     * @param room The room where the player will start.
     */
    public void startPlayer(Room room) {
        if (this.player.getCurrentRoom() == null) {
            this.player.setCurrentRoom(this.rooms.searchNode(room));
            System.out.println(this.player.getNameOfPlayer() + " starts in " + player.getCurrentRoom().getValue().getNameOfRoom() + ".");
        } else {
            System.out.println(this.player.getNameOfPlayer() + " has already started.");
        }
    }

    /**
     * Removes the specified room from the list of rooms.
     * @param toRemove the room to be removed
     * @throws RoomDoesNotExist if the specified room does not exist in the list
     */
    public void removeRoom(Room toRemove){
        try {
            QuartNode<Room> nodeToRemove = this.rooms.searchNode(toRemove);
            this.rooms.remove(toRemove);
            System.out.println(nodeToRemove.getValue().getNameOfRoom() + " was removed from the game.");
            nodeToRemove.getValue().cleanRoom();
        } catch (NoSuchElement e) {
            throw new RoomDoesNotExist();
        }
    }

    /**
     * Adds an item to a room in the game.
     *
     * @param room The room where the item will be added.
     * @param item The item to be added.
     */
    public void addItem(Room room, Item item) {
        QuartNode<Room> RoomToAddTo = this.rooms.searchNode(room);
        if(RoomToAddTo == null) {
            System.out.println("Could not add " + item.getNameOfItem() + " to the game.");
        } else {
            RoomToAddTo.getValue().addItemToRoom(item);
        }
    }

    /**
     * Adds a new room to the list of rooms, connecting it to another room in the specified direction.
     * @param toInsert the room to insert
     * @param target the target room to connect the new room to
     * @param direction the direction in which to connect the new room
     * @throws RoomDoesNotExist if the target room does not exist in the list
     * @throws ExitIsOccupied if there is already a room connected in the specified direction
     */
    public void addRoom(Room toInsert, Room target, Direction direction) {
        boolean roomAlreadyInGame = false;
        try {
            QuartNode<Room> roomToInsert = this.rooms.searchNode(toInsert);
            if (roomToInsert != null && toInsert.equals(roomToInsert.getValue())) {
                roomAlreadyInGame = true;
            }
            if (roomAlreadyInGame) {
                System.out.println("Could not add " + toInsert.getNameOfRoom() + " to the game.");
            } else {
                rooms.add(toInsert, target, direction);
                if (target != null) {
                    System.out.println(toInsert.getNameOfRoom() + " was added and is connected to " + this.rooms.searchNode(target).getValue().getNameOfRoom() + " from the " + direction.toString().toLowerCase() + " exit.");
                } else {
                    System.out.println(toInsert.getNameOfRoom() + " was added.");
                }
            }
        } catch (NoSuchElement e) {
            throw new RoomDoesNotExist();
        } catch (DirectionIsOccupied e) {
            throw new ExitIsOccupied();
        }
    }


    /**
     * Moves the player in a specified direction in the game.
     *
     * @param direction The direction in which the player should move.
     */
    public void movePlayer(Direction direction) {
        if (this.player.getCurrentRoom().getValue().isStatusOfPuzzle() || this.player.getCurrentRoom().getConnectedNode(direction) == null) {
            System.out.println(this.player.getNameOfPlayer() + " could not move via the " + direction.toString().toLowerCase() + " exit.");
        } else {
            Room movedFrom = this.player.getCurrentRoom().getValue();
            this.player.setCurrentRoom(this.player.getCurrentRoom().getConnectedNode(direction));
            System.out.println(this.player.getNameOfPlayer() + " moved from " + movedFrom.getNameOfRoom() + " to " + this.player.getCurrentRoom().getValue().getNameOfRoom() + " via the " + direction.toString().toLowerCase() + " exit.");
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
        } else {
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
        if (!(this.player.getCurrentRoom().getValue() == null)) {
            this.player.solvePuzzle();
        }
    }

    /**
     * Uses the specified item, invoking its useItem method with the current player as the parameter.
     *
     * @param item The item to be used.
     */
    public void useItem(Item item) {
        item.useItem(this.player);
    }

    /**
     * Retrieves the linked list of rooms in the game environment.
     * @return the linked list of rooms
     */
    public QuartlyLinkedList<Room> getRooms() {
        return rooms;
    }

    /**
     * Creates and returns a deep copy of the game manager.
     * @return a deep copy of the game manager
     */
    @Override
    public GameManager clone () {
        try {
            GameManager copy = (GameManager) super.clone();
            copy.player = this.player.clone();
            copy.rooms = this.rooms.clone();
            return copy;
        } catch (CloneNotSupportedException e){
                return null;
            }
    }
}
