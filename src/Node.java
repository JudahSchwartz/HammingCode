public class Node {
    char character = '*';
    int value = 0;
    Node left, right, parent;

    public Node(char c, long value)
    {
        this.value = (int) value;
        character = c;
    }

    public Node(Node nodeOne, Node nodeTwo) {
        value = nodeOne.value + nodeTwo.value;
        left = nodeOne;
        right = nodeTwo;
        nodeTwo.parent = nodeOne.parent = this;
    }
}
