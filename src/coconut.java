import java.util.*;

class CoconutSplat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int s = scanner.nextInt();
        int n = scanner.nextInt();
        
        List<String> hands = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            hands.add(i + "F");  // Player i's folded hands
        }
        int currentHandIndex = 0; 
        while (hands.size() > 1) {
            // Find the next hand to be affected after counting s syllables
            currentHandIndex = (currentHandIndex + s - 1) % hands.size();
            //System.out.println(hands);
            //System.out.println(currentHandIndex);
            String affectedHand = hands.get(currentHandIndex);

            int player = Integer.valueOf(affectedHand.substring(0, affectedHand.length()-1)); //Problem
            char handState = affectedHand.charAt(affectedHand.length()-1); //PROBLEM
            
            // Diff Cases
            if (handState == 'F') {
                hands.set(currentHandIndex, player + "L");  // Change current to left fist
                hands.add(currentHandIndex + 1, player + "R"); // Add right fist right after left
            } else if (handState == 'L') {
                // Left fist -> palm down
                hands.set(currentHandIndex, player + "P"); 
                currentHandIndex++;

            } else if (handState == 'R') {
                // Right fist -> palm down
                hands.set(currentHandIndex, player + "P"); 
                currentHandIndex++;
            } else if (handState == 'P') {
                // Palm down -> hand behind back (remove from list)
                hands.remove(currentHandIndex);
                if (currentHandIndex >= hands.size()) {
                    currentHandIndex = 0; 
                    continue;
                }
                
            }
          }

        String Winner = hands.get(0);
        int W = Integer.valueOf(Winner.substring(0,Winner.length()-1));
        // The last remaining hand belongs to the winning player
        System.out.println(W);
    
        scanner.close();
    }
}
