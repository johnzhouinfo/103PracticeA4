public class Node {
    String label;
    int price;
    Node next;

    public Node() {
        label = "";
        price = 0;
        next = null;
    }

    public Node(String label, int price) {
        this.label = label;
        this.price = price;
        next = null;
    }
}
