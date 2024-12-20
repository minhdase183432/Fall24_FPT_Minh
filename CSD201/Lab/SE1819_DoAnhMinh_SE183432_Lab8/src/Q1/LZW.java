package Q1;

import java.util.HashMap;
import java.util.Map;

public class LZW {
    public static Map<String, Integer> createDictionary() {
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dictionary.put("" + (char) i, i);
        }
        return dictionary;
    }

    public static void main(String[] args) {
        Map<String, Integer> dictionary = createDictionary();
        System.out.println("LZW Dictionary:");
        for (Map.Entry<String, Integer> entry : dictionary.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}