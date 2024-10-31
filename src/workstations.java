import java.util.*;

class Workstation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    
        int n = sc.nextInt(); // number of researchers
        int m = sc.nextInt(); // minutes after which workstations lock themselves

        int[][] researchers = new int[n][2];
        for (int i = 0; i < n; i++) {
            researchers[i][0] = sc.nextInt(); //time of arrival
            researchers[i][1] = sc.nextInt(); //duration of stay
        }
        //Sort by arrival time
        Arrays.sort(researchers, Comparator.comparingInt(r -> r[0]));

        //Min priority queue to store workstation free times
        PriorityQueue<Integer> freeTimes = new PriorityQueue<>();
        int unlocksSaved = 0;
        
        for (int[] researcher : researchers) { //For each researcher
            int arrival = researcher[0];
            int leaveTime = arrival + researcher[1];

            //Remove workstations from the queue that locked themselves
            while (!freeTimes.isEmpty() && freeTimes.peek() + m < arrival) {
                freeTimes.poll();
            }

            //Reuse the workstation if queue.peek < arival time
            if (!freeTimes.isEmpty() && freeTimes.peek() <= arrival) {
                freeTimes.poll(); // reuse the workstation
                unlocksSaved++;
            }
            //Add the time this researcher's workstation will become free
            freeTimes.offer(leaveTime);
        }
        
        
        System.out.println(unlocksSaved);
    }
}
