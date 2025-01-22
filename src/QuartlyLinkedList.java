/**
 * QuartlyLinkedList represents a linked list data structure that allows connections
 * between nodes in four directions: NORTH, SOUTH, EAST, and WEST.
 * @param <T> the type of elements stored in the linked list
 */
import java.util.Iterator;
public class QuartlyLinkedList<T extends Cloneable> implements Cloneable, Iterable<QuartNode<T>>{

    private QuartNode<T> root;

    /**
     * Constructs an empty QuartlyLinkedList.
     */
    public QuartlyLinkedList(){
        this.root = null;
    }

    /**
     * Searches for a node containing the specified value in the linked list.
     * @param target the value to search for
     * @return the node containing the specified value, or null if not found
     */
    public QuartNode<T> searchNode (T target){
        Iterator<QuartNode<T>> it = iterator();
        while (it.hasNext()){
            QuartNode<T> current = it.next();
            if (current.getValue().equals(target)){
                return current;
            }
        } return null;
    }

    /**
     * Adds a new node containing the specified element next to the node containing
     * the specified target element in the specified direction.
     * @param toInsert the element to insert into the new node
     * @param target the target element to find the position for insertion
     * @param direction the direction in which to insert the new node
     * @throws NoSuchElement if the target element is not found in the linked list
     * @throws DirectionIsOccupied if there is already a node in the specified direction
     */
    public void add(T toInsert, T target, Direction direction) {
        QuartNode<T> targetNode = searchNode(target);
        if (this.root == null && target == null) {
            this.root = new QuartNode<>(toInsert);
        } else if (targetNode == null) {
            throw new NoSuchElement();
        } else if (targetNode.getConnectedNode(direction) != null) {
            throw new DirectionIsOccupied();
        } else {
            new QuartNode<>(toInsert, direction, targetNode);
        }
    }

    /**
     * Removes the node containing the specified element from the linked list.
     * @param toRemove the element to remove
     * @throws NoSuchElement if the specified element is not found in the linked list
     */
    public void remove(T toRemove) {
        QuartNode<T> nodeToRemove = searchNode(toRemove);
        if (nodeToRemove == null) {
            throw new NoSuchElement();
        } else {
            if (nodeToRemove == this.root) {
                this.root = null;
            } else {
                Direction[] directions = Direction.values();
                for (Direction direction : directions) {
                    if (nodeToRemove.getConnectedNode(direction) != null) {
                        nodeToRemove.getConnectedNode(direction).setConnectionInOtherDirection(direction, null);
                    }
                }
            }
        }
    }

    /**
     * Creates and returns a deep copy of the linked list.
     * @return a copy of the linked list
     */
    public QuartlyLinkedList<T> clone() {
        try {
            QuartlyLinkedList<T> copy = (QuartlyLinkedList<T>) super.clone();
            copy.root = null;
            if (this.root == null) {
                return copy;
            } else {
                Iterator<QuartNode<T>> it = iterator();
                while (it.hasNext()) {
                    QuartNode<T> original = it.next();
                    QuartNode<T> originalCopy = original.clone();
                    if (copy.root == null) {
                        copy.add(originalCopy.getValue(), null, null);
                    } else {
                        Direction[] directions = Direction.values();
                        for (Direction direction : directions) {
                            if (original.getNeighbor(direction) != null) {
                                QuartNode<T> visited = copy.searchNode(original.getNeighbor(direction).getValue());
                                if (visited == null) {
                                    QuartNode<T> newClone = original.getNeighbor(direction).clone();
                                    originalCopy.setConnectionInDirection(direction, newClone);
                                    newClone.setConnectionInOtherDirection(direction, originalCopy);
                                } else {
                                    originalCopy.setConnectionInDirection(direction, visited);
                                    visited.setConnectionInOtherDirection(direction, originalCopy);
                                }
                            }
                        }
                    }
                }
                return copy;
            }
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * Returns an iterator over the elements in the linked list.
     * @return an iterator over the elements in the linked list
     */
    @Override
    public Iterator<QuartNode<T>> iterator(){
        return new QuartlyLinkedListIterator<>(root);
    }
}
