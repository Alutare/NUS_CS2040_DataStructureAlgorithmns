import java.util.*;

class RelayRace {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); 

        // Arrays to store runner information
        String[] names = new String[n];
        double[] firstLegTimes = new double[n];
        double[] otherLegTimes = new double[n];

        // Read each runner's data
        for (int i = 0; i < n; i++) {
            names[i] = sc.next();               // Runner's name
            firstLegTimes[i] = sc.nextDouble(); // First leg time
            otherLegTimes[i] = sc.nextDouble(); // Other leg time
        }

        double bestTime = Double.MAX_VALUE; //Initialize best time to infinity
        String[] bestTeam = new String[4]; //Initialize new bestteam array

        // Try each runner as the first leg runner
        for (int i = 0; i < n; i++) {
            // Create a list for the other leg runners (excluding the first leg runner)
            List<Integer> otherLegRunners = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    otherLegRunners.add(j);
                }
            }

            // Sort the other leg runners by their "other leg time"
            Collections.sort(otherLegRunners, (a, b) -> Double.compare(otherLegTimes[a], otherLegTimes[b]));

            // Calculate the total time for this combination
            double totalTime = firstLegTimes[i];
            totalTime += otherLegTimes[otherLegRunners.get(0)];
            totalTime += otherLegTimes[otherLegRunners.get(1)];
            totalTime += otherLegTimes[otherLegRunners.get(2)];

            // If this is the best time, update the best time and team
            if (totalTime < bestTime) {
                bestTime = totalTime;
                bestTeam[0] = names[i];
                bestTeam[1] = names[otherLegRunners.get(0)];
                bestTeam[2] = names[otherLegRunners.get(1)];
                bestTeam[3] = names[otherLegRunners.get(2)];
            }
        }

        // Output the best time and the best team
        System.out.printf("%.2f\n", bestTime);
        for (String runnerName : bestTeam) {
            System.out.println(runnerName);
        }
    }
}
