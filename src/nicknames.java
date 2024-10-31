import java.io.*;
import java.util.HashMap;

class Nicknames {
    static class AVLTree {
        private class Node {
            String key;
            int height;
            Node left, right;

            Node(String d) {
                key = d;
                height = 1;
            }
        }

        private Node root;

        private int height(Node N) {
            return (N == null) ? 0 : N.height;
        }

        private Node rightRotate(Node y) {
            Node x = y.left;
            Node T2 = x.right;

            x.right = y;
            y.left = T2;

            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;

            return x;
        }

        private Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;

            y.left = x;
            x.right = T2;

            x.height = Math.max(height(x.left), height(x.right)) + 1;
            y.height = Math.max(height(y.left), height(y.right)) + 1;

            return y;
        }

        private int getBalance(Node N) {
            return (N == null) ? 0 : height(N.left) - height(N.right);
        }

        public void insert(String key) {
            root = insertRec(root, key);
        }

        private Node insertRec(Node node, String key) {
            if (node == null) return new Node(key);

            if (key.compareTo(node.key) < 0)
                node.left = insertRec(node.left, key);
            else if (key.compareTo(node.key) > 0)
                node.right = insertRec(node.right, key);
            else
                return node; // Duplicates are not allowed

            node.height = 1 + Math.max(height(node.left), height(node.right));

            int balance = getBalance(node);
            // Balancing the tree
            if (balance > 1 && key.compareTo(node.left.key) < 0)
                return rightRotate(node);
            if (balance < -1 && key.compareTo(node.right.key) > 0)
                return leftRotate(node);
            if (balance > 1 && key.compareTo(node.left.key) > 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            if (balance < -1 && key.compareTo(node.right.key) < 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        public int countPrefixMatches(String prefix) {
            String upperBound = getUpperBound(prefix);
            return countInRange(root, prefix, upperBound);
        }

        private String getUpperBound(String prefix) {
            return prefix + Character.MAX_VALUE; // Append a high value character
        }

        private int countInRange(Node node, String lower, String upper) {
            if (node == null) return 0;

            if (node.key.compareTo(upper) >= 0) {
                return countInRange(node.left, lower, upper); // Only check the left subtree
            } else if (node.key.compareTo(lower) < 0) {
                return countInRange(node.right, lower, upper); // Only check the right subtree
            } else {
                // Current node is in the range
                return 1 + countInRange(node.left, lower, upper) + countInRange(node.right, lower, upper);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int A = Integer.parseInt(br.readLine());
        HashMap<Character, AVLTree> trees = new HashMap<>();
        HashMap<String, Integer> cache = new HashMap<>(); // Cache for nickname results

        // Read all names and build the AVL trees
        for (int i = 0; i < A; i++) {
            String name = br.readLine();
            char firstLetter = name.charAt(0);
            trees.putIfAbsent(firstLetter, new AVLTree());
            trees.get(firstLetter).insert(name);
        }

        int B = Integer.parseInt(br.readLine());
        // Read all nicknames and process the counts
        for (int i = 0; i < B; i++) {
            String nickname = br.readLine();
            if (cache.containsKey(nickname)) {
                // If result is cached, use it
                pw.write(cache.get(nickname) + "\n");
            } else {
                char firstLetter = nickname.charAt(0);
                AVLTree tree = trees.get(firstLetter);
                int count = (tree != null) ? tree.countPrefixMatches(nickname) : 0;
                cache.put(nickname, count); // Cache the result for future queries
                pw.write(count + "\n");
            }
        }

        pw.flush();
        br.close();
        pw.close();
    }
}
