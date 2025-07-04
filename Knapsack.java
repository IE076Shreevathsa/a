import java.util.*;

public class Knapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] arr = new int[n]; // arr stores weights
        int[] val = new int[n]; // values of items

        System.out.println("Enter weight and value of each item:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();  // weight
            val[i] = sc.nextInt();  // value
        }

        System.out.print("Enter backpack capacity (target): ");
        int target = sc.nextInt();

        int[][] dp = new int[n + 1][target + 1];

        for (int i = 1; i <= n; i++) {
            for (int sum = 0; sum <= target; sum++) {
                if (arr[i - 1] <= sum)
                    dp[i][sum] = Math.max(dp[i - 1][sum], dp[i - 1][sum - arr[i - 1]] + val[i - 1]);
                else
                    dp[i][sum] = dp[i - 1][sum];
            }
        }

        System.out.println("Maximum value that can be carried: " + dp[n][target]);

        int sum = target;
        System.out.println("Selected items:");
        for (int i = n; i > 0 && sum > 0; i--) {
            if (dp[i][sum] != dp[i - 1][sum]) {
                System.out.println("Item (Weight: " + arr[i - 1] + ", Value: " + val[i - 1] + ")");
                sum -= arr[i - 1];
            }
        }
    }
}

