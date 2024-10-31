import java.util.*;

class SignSorter {

    // Method to get the middle character(s) for sorting
    private static String getMiddleLetters(String sign) {
        int n = sign.length();
        if (n % 2 == 1) {
            //Odd length, take middle value
            return String.valueOf(sign.charAt((n-1)/ 2));
        } else {
            //Even length, Substring is from start to excluding end index
            return sign.substring(n / 2 - 1, n / 2 + 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Read the number of signs
        int n = scanner.nextInt();
        scanner.nextLine();

        //Add all signs into an ArrayList
        List<String> signs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            signs.add(scanner.nextLine()); 
        }

        // Sort using a custom comparator
        signs.sort((a,b) -> {
            String middle1 = getMiddleLetters(a);
            String middle2 = getMiddleLetters(b);
            return middle1.compareTo(middle2);
        });


        // Print each sign in the sorted order
        for (String sign : signs) {
            System.out.println(sign);
        }

        scanner.close();
    }
}