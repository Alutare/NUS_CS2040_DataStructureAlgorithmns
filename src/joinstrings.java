import java.io.*;
import java.util.*;

class JoinStrings {
    static class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    static class TailedLinkedList {
        Node head;
        Node tail;

        TailedLinkedList() {
            this.head = null;
            this.tail = null;
        }

        void append(String data) {
            Node newNode = new Node(data);
            if (tail == null) { // If the list is empty
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode; // Link the new node
                tail = newNode; // Update the tail
            }
        }

        void concatenateTo(TailedLinkedList other) {
            if (other.head == null) return; // Nothing to concatenate
            if (this.tail != null) {
                this.tail.next = other.head; // Link the two lists
            } else {
                this.head = other.head; // If this is empty, set head
            }
            this.tail = other.tail; // Update tail to the other list's tail
        }

        String getResult() {
            StringBuilder result = new StringBuilder();
            Node current = head;
            while (current != null) {
                result.append(current.data); 
                current = current.next;
            }
            return result.toString(); // Return the final concatenated string
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // Read number of strings
        TailedLinkedList[] lists = new TailedLinkedList[n]; // Array of linked lists

        for (int i = 0; i < n; i++) {
            lists[i] = new TailedLinkedList(); // Initialize each linked list
            lists[i].append(br.readLine()); // Add the input string to the list
        }

        boolean[] active = new boolean[n];
        Arrays.fill(active, true); // Mark all lists as active

        for (int i = 0; i < n - 1; i++) {
            String[] operation = br.readLine().split(" ");
            int a = Integer.parseInt(operation[0]) - 1; // Convert to 0-based index
            int b = Integer.parseInt(operation[1]) - 1; // Convert to 0-based index

            if (active[a] && active[b]) {
                lists[a].concatenateTo(lists[b]); // Concatenate list b to list a
                active[b] = false; // Mark list b as inactive
            }
        }

        // Find the last active string
        for (int i = 0; i < n; i++) {
            if (active[i]) {
                System.out.println(lists[i].getResult()); // Print the final concatenated result
                break;
            }
        }
    }
}
