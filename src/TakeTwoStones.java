import java.util.Scanner;

public class TakeTwoStones {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        
        // Determine the winner based on the number of stones
        if (N % 2 == 1) {
            System.out.println("Alice");
        } else {
            System.out.println("Bob");
        }
        input.close();
    }
}
