import java.util.*;

public class Q1 {

    public static void main(String[] args) {
        //Start Scanning inputs
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] input = new int[n][n];
        int[][] readyToPrint = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                input[i][j] = scanner.nextInt();
            }
        }

        int m = scanner.nextInt();
        //End of scanning
        //creating hashmap to relate each nenber to its faravani
        HashMap<Integer, Integer> frequency = new HashMap<>();

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                frequency.put(input[x][y], frequency.getOrDefault(input[x][y], 0) + 1);
            }
        }
        //sorting by how many times they are reapited and if we have 2 equal sheddat rang we put bigger number first
        ArrayList<Integer> mostRepeated = new ArrayList<>(frequency.keySet());
        mostRepeated.sort((a, b) -> {
            if (frequency.get(a).equals(frequency.get(b))) {
                return b - a;
            }
            return frequency.get(b) - frequency.get(a);
        });
        //index of most reapited indexes in a int array
        int[] most_repeated = new int[m];
        
        for (int p = 0; p < m; p++) {
            most_repeated[p] = mostRepeated.get(p);
        }

        changeArrayForFirstTime(input, n, m, most_repeated);
        changeValuesTo_0_To_m(input, n, readyToPrint, m);
        printAnArray(readyToPrint, n);
    }
    //change each member to nearest member
    static void changeArrayForFirstTime(int[][] input, int n, int m, int[] most_repeated) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int nowSheddatRang = input[i][j];
                int replaceValue = findClosestColor(nowSheddatRang, most_repeated);
                input[i][j] = replaceValue;
            }
        }
    }
    //Function to output each members the most nearest faravan index
    static int findClosestColor(int now_color, int[] most_repeated) {
        int minDiff = Integer.MAX_VALUE;
        int replaceValue = 0;
        int copy_vlue=0;
        for (int value : most_repeated) {
            int diff = Math.abs(now_color - value);
            if(diff==minDiff && value>copy_vlue){
                minDiff = diff;
                replaceValue = value;
                copy_vlue=value;
            }
            if (diff < minDiff) {
                minDiff = diff;
                replaceValue = value;
                copy_vlue=value;
            }
        }

        return replaceValue;
    }
    //change members of araay from 0 to m-1
    static void changeValuesTo_0_To_m(int[][] input, int n, int[][] readyToPrint, int m) {
        int toReplace = 0;
        
        while (findTotalMember(input, n) != -(n * n) && toReplace <= m - 1) {
            int minimum = findMinimum(input);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (input[i][j] == minimum) {
                        readyToPrint[i][j] = toReplace;
                        input[i][j] = -1;
                    }
                }
            }
            toReplace++;
        }
    }
    //function for printing an array
    static void printAnArray(int[][] readyToPrint, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(readyToPrint[i][j] + " ");
            }
            System.out.println();
        }
    }
    //function to find total of an array
    static int findTotalMember(int[][] input, int n) {
        int sum=0;
        
       for(int i=0;i<n;i++){
           for(int j=0;j<n;j++){
               sum+=input[i][j];
           }
       }
       
       return sum;
    }
    //function to find minimum in an array
    public static int findMinimum(int[][] arr) {
        int min = Integer.MAX_VALUE;
        
        for (int[] row : arr) {
            for (int val : row) {
                if (val < min && val != -1) {
                    min = val;
                }
            }
        }
        
        return min;
    }
}