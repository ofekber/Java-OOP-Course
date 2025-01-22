/**
 * QuartNode represents a node in a linked list data structure that allows connections
 * in four directions: NORTH, SOUTH, EAST, and WEST.
 * @param <T> the type of element stored in the node, must implement Cloneable
 */
import java.lang.reflect.InvocationTargetException;

public class QuartNode<T extends Cloneable> implements Cloneable {
    private T value;
    private QuartNode<T> NORTH;
    private QuartNode<T> SOUTH;
    private QuartNode<T> EAST;
    private QuartNode<T> WEST;

    /**
     * Constructs a QuartNode with the specified value and initializes all direction pointers to null.
     * @param t the value to store in the node
     */
    public QuartNode(T t){
        this.value = t;
        this.NORTH = null;
        this.SOUTH = null;
        this.EAST = null;
        this.WEST = null;
    }

    /**
     * Constructs a QuartNode with the specified value, connecting it to another node in the specified direction.
     * @param t the value to store in the node
     * @param direction the direction in which to connect the new node
     * @param other the node to connect to
     */
    public QuartNode (T t,Direction direction, QuartNode<T> other){
        this.value = t;
        other.setConnectionInDirection(direction, this);
        this.setConnectionInOtherDirection(direction, other);
    }

    /**
     * Retrieves the neighbor node in the specified direction.
     * @param direction the direction of the neighbor node to retrieve
     * @return the neighbor node in the specified direction
     */
    public QuartNode<T> getNeighbor (Direction direction){
        return getConnectedNode(direction);
    }

    /**
     * Sets the connection of this node in the specified direction to the specified node.
     * @param direction the direction in which to set the connection
     * @param other the node to connect to
     */
    public void setConnectionInDirection(Direction direction, QuartNode<T> other) {
        switch (direction) {
            case NORTH:
                this.NORTH = other;
                break;
            case SOUTH:
                this.SOUTH = other;
                break;
            case EAST:
                this.EAST = other;
                break;
            case WEST:
                this.WEST = other;
                break;
        }
    }

    /**
     * Sets the connection of the specified node in the opposite direction to this node.
     * @param direction the direction in which to set the connection
     * @param other the node to connect to
     */
    public void setConnectionInOtherDirection(Direction direction, QuartNode<T> other) {
        switch (direction) {
            case NORTH:
                this.SOUTH = other;
                break;
            case SOUTH:
                this.NORTH = other;
                break;
            case EAST:
                this.WEST = other;
                break;
            case WEST:
                this.EAST = other;
                break;
        }
    }

    /**
     * Retrieves the node connected to this node in the specified direction.
     * @param direction the direction of the connected node to retrieve
     * @return the connected node in the specified direction
     */
    public QuartNode<T> getConnectedNode(Direction direction) {
        switch (direction) {
            case NORTH:
                return this.NORTH;
            case SOUTH:
                return this.SOUTH;
            case EAST:
                return this.EAST;
            case WEST:
                return this.WEST;
        }
        return null;
    }

    /**
     * Retrieves the value stored in this node.
     * @return the value stored in this node
     */
    public T getValue() {
        return value;
    }

    /**
     * Creates and returns a deep copy of this node.
     * @return a deep copy of this node
     */
    @Override
    public QuartNode<T> clone(){
        try {
            QuartNode<T> copy = (QuartNode<T>) super.clone();
            copy.value = (T) this.getValue().getClass().getMethod("clone").invoke(this.getValue());
            copy.NORTH = null;
            copy.SOUTH = null;
            copy.EAST = null;
            copy.WEST = null;
            return copy;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | CloneNotSupportedException e) {
            return null;
        }
    }
}
