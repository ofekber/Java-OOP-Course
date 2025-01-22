/**
 * LargeBag class represents a larger version of a bag, extending the Bag class.
 */
public class LargeBag extends Bag {
    static final int MINIMUM_SIZE_OF_LARGE_BAG = 5;

    /**
     * Constructor for the LargeBag class.
     *
     * @param nameOfItem The name of the large bag.
     * @param value The value of the large bag.
     * @param sizeOfBag The size of the large bag.
     */
    public LargeBag(String nameOfItem, int value ,int sizeOfBag) {
        super(nameOfItem, value, sizeOfBag);
        this.sizeOfBag = defaultInitialization(sizeOfBag);
        this.inventory = new Item[this.sizeOfBag];
    }

    /**
     * Overrides the transferItems method to handle transferring items between large bags.
     *
     * @param oldBag The source bag.
     * @param newBag The destination bag.
     */
    @Override
    public void transferItems(Bag oldBag, Bag newBag) {
        for (int i = 0; i < oldBag.inventory.length; i++) {
            if (!(oldBag.inventory[i] instanceof LargeBag) && oldBag.inventory[i] != null){
                newBag.inventory[i] = oldBag.inventory[i];
                oldBag.inventory[i] = null;
            }
        }
    }

    /**
     * Initializes the size of the large bag based on a minimum size requirement.
     *
     * @param sizeOfBag The size of the large bag.
     * @return The adjusted size of the large bag.
     */
    private int defaultInitialization(int sizeOfBag) {
        if (sizeOfBag >= MINIMUM_SIZE_OF_LARGE_BAG) {
            return sizeOfBag;
        } else {
           return MINIMUM_SIZE_OF_LARGE_BAG;
        }
    }

    /**
     * Overrides the equals method to compare LargeBag objects.
     *
     * @param other The object to compare.
     * @return True if the large bags are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof LargeBag){
            return ((LargeBag) other).sizeOfBag == this.sizeOfBag && ((LargeBag) other).getValue() == this.getValue() && ((LargeBag) other).valueOfItemsInBag() == this.valueOfItemsInBag();
        }
        return false;
    }

    @Override
    public int hashCode(){
        return 91 + super.hashCode();
    }

    /**
     * Overrides the useItem method to handle using a large bag for a player.
     *
     * @param player The player using the item.
     */
    @Override
    public void useItem(Player player) {
        boolean necessaryPlaceForItems = (this.getSizeOfBag() >= necessaryPlaceForItems(player.getBag()));
        boolean isEqual  = (this.getSizeOfBag() == necessaryPlaceForItems(player.getBag()));
        boolean isOldLargeBag = (player.getBag() instanceof LargeBag);
        if (!isItemNear(this, player)) {
            System.out.println(this.getNameOfItem() + " is not near " + player.getNameOfPlayer() + ".");
        } else if (necessaryPlaceForItems) {
            if (isOldLargeBag){
                transferItems(player.getBag(), this);
                Bag tmp = player.getBag();
                player.setBag(this);
                player.getCurrentRoom().setItem(tmp, player.indexOfItemInRoom(this));
                player.disassembleItem(tmp);
                System.out.println(player.getNameOfPlayer() + " is now carrying " + this.getNameOfItem() + ".");
            } else if (!isOldLargeBag) {
                if(!isEqual) {
                    this.inventory[necessaryPlaceForItems(player.getBag())] = player.getBag();
                    transferItems(player.getBag(), this);
                    player.setBag(this);
                    player.getCurrentRoom().setItem(null, player.indexOfItemInRoom(this));
                } else {
                    transferItems(player.getBag(), this);
                    Bag tmp = player.getBag();
                    player.setBag(this);
                    player.getCurrentRoom().setItem(tmp, player.indexOfItemInRoom(this));
                    player.disassembleItem(tmp);
                } System.out.println(player.getNameOfPlayer() + " is now carrying " + this.getNameOfItem() + ".");
            } else {System.out.println(this.getNameOfItem() + " is too small.");}
        }
    }
}

