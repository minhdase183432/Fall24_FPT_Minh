public class Main {
    public static void main(String[] args) {
        ChainingHashTable hashTable = new ChainingHashTable();
        
        // Inserting students into the hash table
        hashTable.insert(new Student("Alice", 20, 8.5));
        hashTable.insert(new Student("Bob", 22, 9.0));
        hashTable.insert(new Student("Charlie", 21, 7.8));
        hashTable.insert(new Student("David", 23, 8.0));
        hashTable.insert(new Student("Eve", 19, 9.4));
        hashTable.insert(new Student("alice", 22, 6.9)); // Collision with "Alice"

        // Display the hash table
        hashTable.display();

        // Search for a student
        String searchName = "Alice";
        Student foundStudent = hashTable.search(searchName);
        if (foundStudent != null) {
            System.out.println("Found: " + foundStudent);
        } else {
            System.out.println(searchName + " not found.");
        }
    }
}