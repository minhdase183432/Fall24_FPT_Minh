package Q1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LZWCompression {
    // Method to create the dictionary for LZW encoding
    public static Map<String, Integer> createDictionary() {
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put("" + (char) i, i);
        }
        return dictionary;
    }

    // Method to compress the input string using LZW algorithm
    public static List<Integer> compress(String input) {
        Map<String, Integer> dictionary = createDictionary();
        List<Integer> compressed = new ArrayList<>();
        String phrase = "";

        for (char c : input.toCharArray()) {
            String newPhrase = phrase + c;
            if (dictionary.containsKey(newPhrase)) {
                phrase = newPhrase;
            } else {
                compressed.add(dictionary.get(phrase));
                dictionary.put(newPhrase, dictionary.size());
                phrase = "" + c;
            }
        }

        // Add the code for the last phrase
        if (!phrase.isEmpty()) {
            compressed.add(dictionary.get(phrase));
        }

        return compressed;
    }

    public static void main(String[] args) {
        String input = "ABABABABA"; // Change this to your input string
        List<Integer> compressed = compress(input);
        System.out.println("Compressed Output:");
        for (int i : compressed) {
            System.out.print(i + " ");
        }
    }
}