import java.io.*;
import java.util.*;

class LongWait {
    public static class CircularArray {
        private int[] array;
        private int size;
        private int head;
        private int tail;

        // Constructor
        public CircularArray(int capacity) {
            array = new int[capacity];
            size = 0;
            head = 0;
            tail = 0;
        }

        // Enqueue (Add Element) Method
        public void enqueue(int element) {
            if (size == array.length) {
                resizeArray();
            }
            array[tail] = element;
            tail = (tail + 1) % array.length;
            size++;
        }

        // Add First Method (Inserts element at the front)
        public void addFirst(int element) {
            if (size == array.length) {
                resizeArray();
            }
            head = (head - 1 + array.length) % array.length; // Wrap around if needed
            array[head] = element; // Insert the new element at the head
            size++;
        }

        // Remove last element in the circular array
        public int removeLast() {
            if (isEmpty()) {
                throw new IllegalStateException("Circular array is empty");
            }
            tail = (tail - 1 + array.length) % array.length; // Adjust tail for wrap-around
            int removedElement = array[tail]; // Remove the element at tail
            size--;
            return removedElement;
        }

        public int removefirst() {
            if (isEmpty()) {
                throw new IllegalStateException("Circular array is empty");
            }
            int removedElement = array[head]; // Get the element at head
            head = (head + 1) % array.length; // Adjust head for wrap-around
            size--;
            return removedElement;
        }

        // Resize the array when full
        private void resizeArray() {
            int newCapacity = array.length * 2;
            int[] newArray = new int[newCapacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[(head + i) % array.length];
            }
            array = newArray;
            head = 0;
            tail = size;
        }

        // Get element at a specific index (1-based indexing)
        public int get(int pos) {
            if (pos < 1 || pos > size) {
                throw new IndexOutOfBoundsException("Invalid position");
            }
            return array[(head + pos - 1) % array.length]; // Convert to 0-indexed
        }

        // Check if CircularArray is empty
        public boolean isEmpty() {
            return size == 0;
        }

        // Get current size of the CircularArray
        public int size() {
            return size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder output = new StringBuilder();
        // Read input for Q and K
        int Q = Integer.parseInt(st.nextToken()); 
        int K = Integer.parseInt(st.nextToken()); 

        CircularArray left = new CircularArray(K);   // Left queue (VIP and member insertions)
        CircularArray right = new CircularArray(Q);  // Right queue (regular enqueues)

        // Processing each operation
        for (int i = 0; i < Q; i++) {
            String operation = br.readLine();
            st = new StringTokenizer(operation);
            String command = st.nextToken(); 

            if (command.equals("queue")) {  // Add to the right (regular queue)
                int id = Integer.parseInt(st.nextToken());
                right.enqueue(id);

            } else if (command.equals("vip")) {  // Add to the front (VIPs to the left)
                int id = Integer.parseInt(st.nextToken());
                left.addFirst(id);

            } else if (command.equals("member")) {  // Insert at K or less
                int id = Integer.parseInt(st.nextToken());
                while (left.size() > K) {
                    int removed = left.removeLast();
                    right.addFirst(removed);
                }
                while(left.size()<K && !right.isEmpty()){
                    int removed1 = right.removefirst();
                    left.enqueue(removed1);
                }

                if (left.size() < K){
                    left.enqueue(id);
                } else {
                    right.addFirst(id);
                }

            } else if (command.equals("slower")) {  // Increase K
                K = Math.min(900000, K + 1);

                while (left.size() > K) {
                    int removed = left.removeLast();
                    right.addFirst(removed);
                }
                while(left.size()<K && !right.isEmpty()){
                    int removed1 = right.removefirst();
                    left.enqueue(removed1);

            } 
        }
            else if (command.equals("faster")) {  // Decrease K
                K = Math.max(1, K - 1);
                while (left.size() > K) {
                    int removed = left.removeLast();
                    right.addFirst(removed);
                }
                while(left.size()<K && !right.isEmpty()){
                    int removed1 = right.removefirst();
                    left.enqueue(removed1);
                }

            } else if (command.equals("findID")) {  // Find the ID of the person at position
                int pos = Integer.parseInt(st.nextToken());
                if (pos <= left.size()) {  // Check in the left queue
                    output.append(left.get(pos)).append("\n");
                } else {  // Check in the right queue
                    output.append(right.get(pos - left.size())).append("\n");
                }
            }
        }
        System.out.println(output.toString());
    }
}