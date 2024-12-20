package Q1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HuffmanFromFile {
    public static void main(String[] args) {
        String filePath = "path/to/your/textfile.txt"; // Change this to your file path
        Map<Character, Integer> frequencyMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int ch;
            while ((ch = br.read()) != -1) {
                char character = (char) ch;
                frequencyMap.put(character, frequencyMap.getOrDefault(character, 0) + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        char[] characters = new char[frequencyMap.size()];
        int[] frequencies = new int[frequencyMap.size()];
        int index = 0;
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            characters[index] = entry.getKey();
            frequencies[index] = entry.getValue();
            index++;
        }

        HuffmanCoding huffmanCoding = new HuffmanCoding();
        HuffmanNode root = huffmanCoding.buildHuffmanTree(characters, frequencies);
        huffmanCoding.printCodes(root, "");
    }
}