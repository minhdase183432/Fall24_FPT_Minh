import java.util.Scanner;

class Node {
    int value;
    Node left, right;

    public Node(int value) {
        this.value = value;
        left = right = null;
    }
}

class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
        root = null;
    }

    boolean isEmpty() {
        return root == null;
    }

    void clear() {
        root = null;
    }

    Node search(Node root, int value) {
        if (root == null || root.value == value) {
            return root;
        }
        if (value < root.value) {
            return search(root.left, value);
        } else {
            return search(root.right, value);
        }
    }

    void insert(int value) {
        root = insertRec(root, value);
    }

    Node insertRec(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }
        return root;
    }

    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.value + " ");
            inorder(root.right);
        }
    }

    void preorder(Node root) {
        if (root != null) {
            System.out.print(root.value + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.value + " ");
        }
    }

    int count(Node root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + count(root.left) + count(root.right);
        }
    }

    Node minValue(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    Node deleteRec(Node root, int value) {
        if (root == null) return root;

        if (value < root.value) {
            root.left = deleteRec(root.left, value);
        } else if (value > root.value) {
            root.right = deleteRec(root.right, value);
        } else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            root.value = minValue(root.right).value;
            root.right = deleteRec(root.right, root.value);
        }
        return root;
    }

    void delete(int value) {
        root = deleteRec(root, value);
    }

    // Additional methods like sum(), avg(), height() can be added here.
}

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);
        int choice, value;

        do {
            System.out.println("\nBinary Search Tree Operations:");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Display Inorder");
            System.out.println("5. Display Preorder");
            System.out.println("6. Display Postorder");
            System.out.println("7. Count Nodes");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    value = scanner.nextInt();
                    bst.insert(value);
                    System.out.println("Value inserted.");
                    break;

                case 2:
                    System.out.print("Enter value to delete: ");
                    value = scanner.nextInt();
                    bst.delete(value);
                    System.out.println("Value deleted.");
                    break;

                case 3:
                    System.out.print("Enter value to search: ");
                    value = scanner.nextInt();
                    Node result = bst.search(bst.root, value);
                    if (result != null) {
                        System.out.println("Value " + value + " found in the tree.");
                    } else {
                        System.out.println("Value " + value + " not found.");
                    }
                    break;

                case 4:
                    System.out.println("Inorder Traversal:");
                    bst.inorder(bst.root);
                    System.out.println();
                    break;

                case 5:
                    System.out.println("Preorder Traversal:");
                    bst.preorder(bst.root);
                    System.out.println();
                    break;

                case 6:
                    System.out.println("Postorder Traversal:");
                    bst.postorder(bst.root);
                    System.out.println();
                    break;

                case 7:
                    System.out.println("Number of nodes: " + bst.count(bst.root));
                    break;

                case 0:
                    System.out.println("Exiting.");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
