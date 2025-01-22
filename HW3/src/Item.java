/**
 * The Item class represents an item with a name.
 */
public abstract class Item {

    /**
     * The name of the item.
     */
    protected String nameOfItem;
    protected int value;

    /**
     * Constructs an Item with the specified name.
     *
     * @param nameOfItem The name of the item.
     */
    public Item(String nameOfItem, int value) {
        this.nameOfItem = nameOfItem;
        this.value = value;
    }

    /**
     * Gets the name of the item.
     *
     * @return The name of the item.
     */
    public String getNameOfItem() {
        return nameOfItem;
    }

    /**
     * Gets the value of the item.
     *
     * @return The value of the item.
     */
    public int getValue() {
        return value;
    }

    /**
     * Checks if the specified item is in the player's current room.
     *
     * @param item   The item to check for.
     * @param player The player whose current room is checked.
     * @return True if the item is in the room, false otherwise.
     */
    public boolean isItemInRoom(Item item, Player player) {
        boolean itemInRoom = false;
        if (player.getCurrentRoom().getItems()[0] != null && player.getCurrentRoom().getItems()[0].equals(item) || player.getCurrentRoom().getItems()[1] != null && player.getCurrentRoom().getItems()[1].equals(item)) {
            itemInRoom = true;
        }
        return itemInRoom;
    }

    /**
     * Checks if the specified item is in the player's bag.
     *
     * @param item   The item to check for.
     * @param player The player whose bag is checked.
     * @return True if the item is in the bag, false otherwise.
     */
    public  boolean isItemInBag (Item item, Player player){
        boolean itemInBag = false;
        for (int i = 0; i < player.getBag().inventory.length; i++) {
            if (player.getBag().inventory[i] != null && player.getBag().inventory[i].equals(item)) {
                itemInBag = true;
            }
        }
        return itemInBag;
    }

    /**
     * Checks if the specified item is near the player (in the room or the bag).
     *
     * @param item   The item to check for.
     * @param player The player whose surroundings are checked.
     * @return True if the item is near the player, false otherwise.
     */
    public boolean isItemNear (Item item, Player player){
        return (isItemInBag(this, player) || isItemInRoom(this, player));
    }

    /**
     * Finds the actual instance of the item near the player (in the room or the bag).
     *
     * @param item   The item to check for.
     * @param player The player whose surroundings are checked.
     * @return The actual instance of the item near the player.
     */
    protected Item itemEquals (Item item, Player player){
        for (int i = 0; i < player.getBag().inventory.length; i++) {
            if (player.getBag().inventory[i] != null && player.getBag().inventory[i].equals(item)) {
                return player.getBag().inventory[i];
            }
        } if (player.getCurrentRoom().getItems()[0] != null && player.getCurrentRoom().getItems()[0].equals(item)){
            return player.getCurrentRoom().getItems()[0];
        } if(player.getCurrentRoom().getItems()[1] != null && player.getCurrentRoom().getItems()[1].equals(item)) {
            return player.getCurrentRoom().getItems()[1];
        }
        return item;
    }

    /**
     * Overrides the default hashCode method with a placeholder value.
     *
     * @return A constant hash code value.
     */
    @Override
    public int hashCode() {
        return 0;
    }

    /**
     * Abstract method to be implemented by subclasses for using the item in the game.
     *
     * @param player The player using the item.
     */
    public abstract void useItem(Player player);
}
