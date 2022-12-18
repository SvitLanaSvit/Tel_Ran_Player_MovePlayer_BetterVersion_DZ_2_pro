package binaryTree;
import java.util.LinkedList;
import java.util.Queue;

public class Node<T extends Comparable<T>>{
    private T value;
    private Node<T> left;
    private Node<T> right;

    public T getValue(){
        return value;
    }

    private boolean isNotExists(Node<T> node) {
        return node == null || node.value == null;
    }

    public void createNode(Node<T> node, T value) {
        node.left = new Node<T>();
        node.right = new Node<T>();
        node.value = value;
    }

    public void insert(Node<T> node, T value) {
        if (isNotExists(node))
            createNode(node, value);
        else if (value.compareTo(node.value) < 0) {
            insert(node.left, value);
        } else
            insert(node.right, value);
    }

    private Node<T> search(Node<T> node, T value) {
        if (isNotExists(node)) {
            return null;
        }
        if (value.compareTo(node.value) == 0) {
            return node;
        }
        if (value.compareTo(node.value) < 0) {
            return search(node.left, value);
        }
        return search(node.right, value);
    }

    public Node<T> getMin(Node<T> node) {
        if (isNotExists(node)) return null;
        if (isNotExists(node.left)) return node;
        return getMin(node.left);
    }

    public Node<T> getMax(Node<T> node) {
        if (isNotExists(node)) return null;
        if (isNotExists(node.right)) return node;
        return getMax(node.right);
    }

    public void inOrderTraversal(Node<T> node) { //symmetric
        if (isNotExists(node)) return;
        inOrderTraversal(node.left);
        System.out.print(node.value + "\n");
        inOrderTraversal(node.right);
    }

    public void postOrderTraversal(Node<T> node) { //inverted
        if(isNotExists(node)){
            return;
        }
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print(node.value + "\n");
    }

    public void directOrderTraversal(Node<T> node) { //direct
        if(isNotExists(node)){
            return;
        }
        System.out.print(node.value + "\n");
        directOrderTraversal(node.left);
        directOrderTraversal(node.right);
    }

    private void moveNode(Node<T> toNode, Node<T> fromNode) {
        toNode.value = fromNode.value;
        toNode.left = fromNode.left;
        toNode.right = fromNode.right;
    }


    public int getSize(Node<T> node){
        if (isNotExists(node))
            return 0;

        // Initialize empty queue.
        Queue<Node<T>> queue = new LinkedList<>();

        // Do level order traversal starting from root
        queue.add(node);

        int count = 0; // Initialize count of full nodes
        while (!queue.isEmpty())
        {
            Node<T> temp = queue.poll();
            if (temp.left != null && temp.right != null)
                count++;

            // Enqueue left child
            if (temp.left != null)
            {
                queue.add(temp.left);
            }

            // Enqueue right child
            if (temp.right != null)
            {
                queue.add(temp.right);
            }
        }
        return count;
    }

    private int getChildrenCount(Node<T> node) {
        int count = 0;
        if (!isNotExists(node.left)) {
            count += 1;
        }
        if (!isNotExists(node.right)) {
            count += 1;
        }
        return count;
    }

    public boolean remove(Node<T> root, T value){
        Node<T> nodeToDelete = search(root, value);
        if(isNotExists(nodeToDelete)) {
            return false;
        }
        int childrenCount = getChildrenCount(nodeToDelete);
        if(childrenCount < 2) {
            removeNodeWithOneOrChildWithoutChildren(nodeToDelete);
        }
        else{
            Node<T> minNode = getMin(nodeToDelete.right);
            assert minNode != null;
            nodeToDelete.value = minNode.value;
            removeNodeWithOneOrChildWithoutChildren(minNode);
        }
        return true;
    }

    private void removeNodeWithOneOrChildWithoutChildren(Node<T> nodeToDelete){
        Node<T> childOrNull = getChildOrNull(nodeToDelete);
        moveNode(nodeToDelete, childOrNull);
    }

    private Node<T> getChildOrNull(Node<T> node){
        return isNotExists(node.left) ? node.right : node.left;
    }
}
