import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Knapsack {

    Node list = new Node();
    Node result = new Node();
    Boolean flag = false;

    public Knapsack() {
        try {
//            Scanner scanner = new Scanner(new File("simplecase.txt"));
            Scanner scanner = new Scanner(new File("productcatalog.txt"));
            Node p = list;
            while (scanner.hasNext()) {
                String item = scanner.nextLine();
                String[] part = item.split("(?<=\\D)(?=\\d)");
                int price = Integer.parseInt(part[1]);
                p.next = new Node(part[0], price);
                p = p.next;
            }

            scanner = new Scanner(System.in);
            while (true) {
                initResultList();
                System.out.println("Please enter the target price:");
                int target = scanner.nextInt();
                if (target == -1) {
                    break;
                }
                switch (target) {
                    case -1:
                        break;
                    case 0:
                        System.out.println("No product selection to purchase.");
                        System.out.println("-----\n");
                    default:
                        find(target, 0, list.next);
                        printList(result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initResultList() {
        result = new Node();
        flag = false;
    }

    private void find(int target, int sum, Node item) {
        Node p = item;
        while (p != null) {
            int currSum = sum;
            currSum += p.price;
            if (currSum == target) {
                flag = true;
                addNode(p);
                return;
            } else if (currSum < target) {
                find(target, currSum, p.next);
            }
            if (flag) {
                addNode(p);
                return;
            }
            p = p.next;
        }
    }

    private void addNode(Node item) {
        Node newNode = new Node(item.label, item.price);
        if (result.next == null) {
            result.next = newNode;
        } else {
            Node temp = result.next;
            result.next = newNode;
            newNode.next = temp;
        }
    }

    private void printList(Node list) {
        Node p = list.next;
        if (p == null) {
            System.out.println("No product selection to purchase.");
        }
        while (p != null) {
            System.out.println(p.label + " : " + p.price);
            p = p.next;
        }
        System.out.println("-----\n");
    }

    public static void main(String[] arg) {
        new Knapsack();
    }
}
