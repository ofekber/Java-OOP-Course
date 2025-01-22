/**
 * The Item class represents an item with a name.
 */
public class Item {

    /**
     * The name of the item.
     */
    private String nameOfItem;

    /**
     * Constructs an Item with the specified name.
     *
     * @param nameOfItem The name of the item.
     */
    public Item (String nameOfItem){
        this.nameOfItem = nameOfItem;
    }

    /**
     * Gets the name of the item.
     *
     * @return The name of the item.
     */
    public String getNameOfItem() {
        return nameOfItem;
    }
}
