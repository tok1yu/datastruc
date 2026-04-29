import java.util.Scanner;

class Node {
    int data;
    Node left, right;

    Node(int value) {
        data = value;
        left = right = null;
    }
}

class BinaryTree {
    Node root;

    BinaryTree() {
        root = null;
    }

    Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.data) {
            System.out.println(value + " goes to the LEFT subtree of " + node.data);
            node.left = insert(node.left, value);
        } else if (value > node.data) {
            System.out.println(value + " goes to the RIGHT subtree of " + node.data);
            node.right = insert(node.right, value);
        } else {
            System.out.println(value + " already exists in the tree.");
        }

        return node;
    }

    void getInput(int value) {
        System.out.println("\nInserting: " + value);
        root = insert(root, value);
    }

    boolean searchValue(Node node, int value) {
        if (node == null) return false;
        if (node.data == value) return true;

        if (value < node.data)
            return searchValue(node.left, value);
        else
            return searchValue(node.right, value);
    }

    void searchValue(int value) {
        boolean found = searchValue(root, value);
        if (found)
            System.out.println("Value " + value + " FOUND in the tree.");
        else
            System.out.println("Value " + value + " NOT FOUND in the tree.");
    }

    void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    void displayTree() {
        System.out.print("\nBinary Tree: ");
        inOrder(root);
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree tree = new BinaryTree();
        int choice;

        do {
            System.out.println("\n===== Binary Tree Menu =====");
            System.out.println("1. Insert Value");
            System.out.println("2. Search Value");
            System.out.println("3. Display Tree");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter integer value to insert: ");
                    int val = scanner.nextInt();
                    tree.getInput(val);
                    break;
                case 2:
                    System.out.print("Enter value to search: ");
                    int searchVal = scanner.nextInt();
                    tree.searchValue(searchVal);
                    break;
                case 3:
                    tree.displayTree();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        scanner.close();
    }
}