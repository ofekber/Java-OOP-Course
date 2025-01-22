/**
 * Bag class represents a container for items, extending the Item class.
 */
public class Bag extends Item {
    static final int MORE_THAN_ONE_BAG = 2;
    protected int sizeOfBag;
    protected Item[] inventory;

    /**
     * Constructor for the Bag class.
     *
     * @param nameOfItem The name of the bag.
     * @param value The value of the bag.
     * @param sizeOfBag The size of the bag.
     */
    public Bag(String nameOfItem, int value, int sizeOfBag) {
        super(nameOfItem, value);
        this.sizeOfBag = sizeOfBag;
        this.inventory = new Item[sizeOfBag];
    }

    /**
     * Counts the number of bags in the inventory.
     *
     * @param bag The bag to check.
     * @return The count of bags in the inventory.
     */
    protected int moreThanOneBag (Bag bag) {
        int counter = 0;
        for (int i = 0; i < bag.inventory.length; i++) {
            if (bag.inventory[i] instanceof Bag) {
                counter += 1;
                if (counter == MORE_THAN_ONE_BAG)
                    return counter;
            }
        }
        return counter;
    }

    /**
     * Gets the size of the bag.
     *
     * @return The size of the bag.
     */
    public int getSizeOfBag() {
        return sizeOfBag;
    }

    /**
     * Calculates the total value of items in the bag.
     *
     * @return The total value of items in the bag.
     */
    public int valueOfItemsInBag() {
        int valueOfItemsInBag = 0;
        for (int i = 0; i < this.inventory.length; i++) {
            if (this.inventory[i] != null) {
                valueOfItemsInBag += this.inventory[i].getValue();
            }
        }
        return valueOfItemsInBag;
    }

    /**
     * Transfers items from one bag to another.
     *
     * @param oldBag The source bag.
     * @param newBag The destination bag.
     */
    public void transferItems(Bag oldBag, Bag newBag) {
        int j = 0;
        for (int i = 0; i < oldBag.inventory.length; i++) {
            if (oldBag.inventory[i] != null){
                newBag.inventory[j] = oldBag.inventory[i];
                oldBag.inventory[i] = null;
                j++;
            }
        }
    }

    /**
     * Overrides the equals method to compare Bag objects.
     *
     * @param other The object to compare.
     * @return True if the bags are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Bag) {
            return this.hashCode() == other.hashCode();
        }
        return false;
    }

    /**
     * Overrides the hashCode method for Bag objects.
     *
     * @return The hash code for the bag.
     */

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + this.getSizeOfBag();
        result = 31 * result + this.getValue();
        result = 31 * result + this.valueOfItemsInBag();

        return result;
    }


    /**
     * Calculates the necessary space for items in the bag.
     *
     * @param bag The bag to check.
     * @return The necessary space for items in the bag.
     */
    protected int necessaryPlaceForItems (Bag bag){
        int nullCounter =0;
        for (int i=0; i < bag.getSizeOfBag(); i++){
            if (bag.inventory[i] == null){
                nullCounter += 1;
            }
        }
        return bag.inventory.length - nullCounter;
    }

    /**
     * Overrides the useItem method to handle bag switching for a player.
     *
     * @param player The player using the item.
     */
    @Override
    public void useItem(Player player) {
        if (!isItemNear(this, player)) {
            System.out.println(player.getBag().getNameOfItem() + " is not near " + player.getNameOfPlayer() + ".");
        }
        switch (moreThanOneBag(player.getBag())) {
            case 2:
                System.out.println(this.getNameOfItem() + " is too small.");
                break;
            case 1:
                if (isItemInRoom(this, player)) {
                    System.out.println(this.getNameOfItem() + " is too small.");
                } else if (isItemInBag(this, player)) {
                    bagSwitch(player);
                }
                break;
            case 0:
                bagSwitch(player);
                break;
        }
    }

    /**
     * Switches the bag for the player, transferring items if space is available.
     *
     * @param player The player switching the bag.
     */
    public void bagSwitch(Player player){
        boolean spaceInBag = (this.getSizeOfBag() >= necessaryPlaceForItems(player.getBag()));
        if (spaceInBag) {
            transferItems(player.getBag(), this);
            Bag tmp = player.getBag();
            player.setBag(this);
            if (isItemInRoom(this, player)){
                player.getCurrentRoom().setItem(tmp, player.indexOfItemInRoom(this));
                player.disassembleItem(tmp);
            } else if (isItemInBag(this, player)) {
                System.out.println(player.getNameOfPlayer() + " disassembled " + tmp.getNameOfItem() + ".");
            }
            System.out.println(player.getNameOfPlayer() + " is now carrying " + this.getNameOfItem() + ".");
        } else {
            System.out.println(this.getNameOfItem() + " is too small.");
        }
    }
}