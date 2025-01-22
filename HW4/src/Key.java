/**
 * The Key class represents a key item that can be used to unlock puzzles in the game.
 * It extends the Item class.
 */
public class Key extends Item implements Cloneable {

    /**
     * Constructs a new Key instance with the specified name and value.
     *
     * @param nameOfItem The name of the key.
     * @param value      The value of the key.
     */
    public Key(String nameOfItem, int value) {
        super(nameOfItem, value);
    }

    /**
     * Overrides the equals method to compare Key objects based on their values.
     *
     * @param other The object to compare.
     * @return True if the keys have the same value, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof Key) && ((Key) other).getValue() == this.getValue();
    }

    /**
     * Overrides the hashCode method to generate a hash code based on the key's value.
     *
     * @return The hash code for the key.
     */
    @Override
    public int hashCode() {
        return this.getValue();
    }

    /**
     * Checks if the key is near the specified item and player.
     * Prints a message if the key is not near the player.
     *
     * @param item   The item to check proximity to.
     * @param player The player to check proximity to.
     */
    public void isKeyNear(Item item, Player player) {
        if (isItemNear(this, player)) {
            System.out.println(item.getNameOfItem() + " is not near " + player.getNameOfPlayer() + ".");
        }
    }

    /**
     * Transfers the key from the player's inventory or room to null, effectively removing it.
     *
     * @param player The player from whom the key is transferred.
     */
    public void transferKey(Player player) {
        if (player.indexOfItemInBag(this) != -1) {
            player.getBag().inventory[player.indexOfItemInBag(this)] = null;
        } else if (player.indexOfItemInRoom(this) != -1) {
            player.getCurrentRoom().getValue().setItem(null, player.indexOfItemInRoom(this));
        }
    }

    /**
     * Overrides the useItem method to handle using the key to unlock puzzles in the player's current room.
     * Prints messages based on the outcome of using the key.
     *
     * @param player The player using the key.
     */
    @Override
    public void useItem(Player player) {
        if (isItemNear(this, player) && !player.getCurrentRoom().getValue().isKeyUsed()) {
            System.out.println(player.getNameOfPlayer() + " used " + itemEquals(this, player).getNameOfItem() + " in " + player.getCurrentRoom().getValue().getNameOfRoom() + ".");
            player.getCurrentRoom().getValue().setKeyUsed(true);
            player.getCurrentRoom().getValue().setStatusOfPuzzle(false);
            player.getCurrentRoom().getValue().setPlaceForKey(this);
            transferKey(player);
        } else if (isItemNear(this, player) && player.getCurrentRoom().getValue().isKeyUsed()) {
            System.out.println(player.getCurrentRoom().getValue().getNameOfRoom() + " was already unlocked.");
        } else if (!isItemNear(this, player)) {
            System.out.println(this.getNameOfItem() + " is not near " + player.getNameOfPlayer() + ".");
        }
    }

    /**
     * Creates and returns a deep copy of this Key object.
     * This method overrides the clone method of the Object class.
     *
     * @return A new Key object that is an exact copy of this Key.
     *
     * @see Item#clone()
     */
    @Override
    public Key clone() {
        Key copy = (Key) super.clone();
        copy.nameOfItem = this.nameOfItem;
        return copy;
    }
}