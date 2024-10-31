import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read the input string
        String input = scanner.nextLine();
        
        // Initialize a StringBuilder to store the result
        StringBuilder shortVariation = new StringBuilder();
        
        // Split the input string by hyphen
        String[] names = input.split("-");
        
        // Append the first letter of each name to the result
        for (String name : names) {
            shortVariation.append(name.charAt(0));
        }
        
        // Print the result
        System.out.println(shortVariation.toString());
        
        // Close the scanner
        scanner.close();
    }
}
