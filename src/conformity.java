import java.util.*;

class Conformity{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        
        //Map to store the frequency of each combination
        HashMap<String, Integer> courseMap = new HashMap<>();
        
        //Reading each frosh's courses
        for (int i = 0; i < n; i++) {
            int[] courses = new int[5];
            
            //Read the 5 course numbers 
            for (int j = 0; j < 5; j++) {
                courses[j] = scanner.nextInt();
            }
            //Sort the courses
            Arrays.sort(courses);
            
            //Convert the sorted array into a string to use as the map key
            String combination = Arrays.toString(courses);
            
            //Update the frequency of this combination in the map
            int value = courseMap.getOrDefault(combination, 0);
            courseMap.put(combination, value + 1);
        }
        
        //Find the maximum frequency
        int maxFrequency = 0;
        for (int count : courseMap.values()) {
            if (count > maxFrequency) {
                maxFrequency = count;
            }
        }
        
        //Calculate the total number of frosh taking the most popular combinations
        int result = 0;
        for (int count : courseMap.values()) {
            if (count == maxFrequency) {
                result += count;
            }
        }
        System.out.println(result);
        scanner.close();
    }
}
