package Q1;

import java.util.PriorityQueue;
import java.util.Comparator;

class HuffmanNode {
    char character;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }
}

class HuffmanCoding {
    public HuffmanNode buildHuffmanTree(char[] characters, int[] frequencies) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.frequency));

        for (int i = 0; i < characters.length; i++) {
            queue.add(new HuffmanNode(characters[i], frequencies[i]));
        }

        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();
            HuffmanNode newNode = new HuffmanNode('\0', left.frequency + right.frequency);
            newNode.left = left;
            newNode.right = right;
            queue.add(newNode);
        }

        return queue.poll();
    }

    public void printCodes(HuffmanNode root, String code) {
        if (root == null) return;

        if (root.left == null && root.right == null) {
            System.out.println(root.character + ": " + code);
        }

        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }
}

public class HuffmanExample {
    public static void main(String[] args) {
        char[] characters = {'a', 'b', 'c', 'd', 'e', 'f'};
        int[] frequencies = {5, 9, 12, 13, 16, 45};

        HuffmanCoding huffmanCoding = new HuffmanCoding();
        HuffmanNode root = huffmanCoding.buildHuffmanTree(characters, frequencies);
        huffmanCoding.printCodes(root, "");
    }
}