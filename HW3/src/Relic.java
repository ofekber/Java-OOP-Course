/**
 * Relic class represents a special item with historical or cultural significance, extending the Item class.
 */
public class Relic extends Item {

    /**
     * Constructor for the Relic class.
     *
     * @param nameOfItem The name of the relic.
     * @param value The value of the relic.
     */
    public Relic(String nameOfItem, int value){
        super(nameOfItem, value);
    }

    /**
     * Overrides the hashCode method to provide a custom hash code based on the value of the relic.
     *
     * @return The hash code for the relic.
     */
    @Override
    public int hashCode() {
        return this.getValue();
    }

    /**
     * Overrides the equals method to compare Relic objects based on their values.
     *
     * @param other The object to compare.
     * @return True if the relics are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof Relic) && ((Relic) other).getValue() == this.getValue();
    }

    /**
     * Overrides the useItem method to handle inspecting the relic.
     *
     * @param player The player using the item.
     */
    @Override
    public void useItem(Player player) {
        if (isItemNear(this, player)) {
            System.out.println(player.getNameOfPlayer() + " is inspecting " + itemEquals(this, player).getNameOfItem() + ".");
        } else {
            System.out.println(this.nameOfItem + " is not near " + player.getNameOfPlayer() + ".");
        }
    }

}
