import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;


public class QuartlyLinkedListIterator<T extends Cloneable> implements Iterator<QuartNode<T>> {
    private Set<QuartNode<T>> visited;
    private Stack<QuartNode<T>> stack;
    private QuartNode<T> nextNode;

    public QuartlyLinkedListIterator(QuartNode<T> nextNode){
        this.nextNode = nextNode;
        visited = new HashSet<>();
        stack = new Stack<>();
    }

    @Override
    public boolean hasNext(){
        return nextNode != null;
    }


    @Override
    public QuartNode<T> next(){
        QuartNode<T> prev = nextNode;

        // Push nextNode to stack and add it to visited list
        stack.push(prev);
        visited.add(prev);

        // Define the order of checking neighbors
        Direction[] directions = Direction.values();

        while (!stack.isEmpty()) {
            QuartNode<T> current = stack.peek();
            for (Direction direction : directions) {
                QuartNode<T> neighbor = current.getNeighbor(direction);
                if (neighbor != null && !visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                    nextNode = neighbor;
                    return prev;
                }
            }
            stack.pop();
        }

        // No more nodes left to traverse
        nextNode = null;
        return prev;
    }
}
