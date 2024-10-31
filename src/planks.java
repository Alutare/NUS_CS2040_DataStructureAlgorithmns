import java.util.*;

class PlankManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int queryCount = scanner.nextInt();
        TreeMap<Integer, TreeMap<Integer, Integer>> plankStorage = new TreeMap<>();

        for (int i = 0; i < queryCount; i++) {
            char operation = scanner.next().charAt(0);

            if (operation == 'a') {
                int weight = scanner.nextInt();
                int length = scanner.nextInt();

                // Check if length already exists; if not, create a new TreeMap for weights
                TreeMap<Integer, Integer> weights = plankStorage.get(length);
                if (weights == null) {
                    weights = new TreeMap<>();
                    plankStorage.put(length, weights);
                }
                // Merge the weight into the weights map
                weights.merge(weight, 1, Integer::sum);
            } else if (operation == 'c') {
                int targetLength = scanner.nextInt();
                int[] leftPlank = getPlank(plankStorage, targetLength, true);
                int[] rightPlank = getPlank(plankStorage, targetLength, false);
                System.out.println((1L + leftPlank[1] + rightPlank[1]) * (1L + Math.abs(leftPlank[0] - rightPlank[0])));
            }
        }
        scanner.close();
    }

    static int[] getPlank(TreeMap<Integer, TreeMap<Integer, Integer>> planks, int target, boolean isFloor) {
        Integer lengthKey;
        if (isFloor) {
            lengthKey = planks.floorKey(target);
        } else {
            lengthKey = planks.ceilingKey(target);
        }

        if (lengthKey == null) {
            return new int[]{0, 0}; // Return defaults if no planks exist
        }

        TreeMap<Integer, Integer> weights = planks.get(lengthKey);
        Integer weightKey;
        if (isFloor) {
            weightKey = weights.firstKey();
        } else {
            weightKey = weights.lastKey();
        }

        if (weights.get(weightKey) == 1) {
            weights.remove(weightKey);
            if (weights.isEmpty()) {
                planks.remove(lengthKey);
            }
        } else {
            weights.put(weightKey, weights.get(weightKey) - 1);
        }

        return new int[]{lengthKey, weightKey};
    }
}

