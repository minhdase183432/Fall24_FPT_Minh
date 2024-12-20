
import java.util.LinkedList;

class ChainingHashTable {
    private LinkedList<Student>[] table;
    private static final int SIZE = 26; // Size of the hash table

    @SuppressWarnings("unchecked")
    public ChainingHashTable() {
        table = new LinkedList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    // Hash function to get index based on the first letter of the name
    private int hash(String name) {
        char firstChar = Character.toLowerCase(name.charAt(0));
        return firstChar - 'a'; // Transform 'a' to 0, 'b' to 1, ..., 'z' to 25
    }

    // Method to insert a student into the hash table
    public void insert(Student student) {
        int index = hash(student.name);
        table[index].add(student);
    }

    // Method to display all students in the hash table
    public void display() {
        for (int i = 0; i < SIZE; i++) {
            if (!table[i].isEmpty()) {
                System.out.println("Index " + i + ": " + table[i]);
            }
        }
    }

    // Method to search for a student by name
    public Student search(String name) {
        int index = hash(name);
        for (Student student : table[index]) {
            if (student.name.equalsIgnoreCase(name)) {
                return student; // Return the found student
            }
        }
        return null; // Not found
    }
}

