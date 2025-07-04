import java.util.*;

public class SubsetSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];  // arr stores item weights or transaction amounts

        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.print("Enter target sum: ");
        int target = sc.nextInt();  // same as capacity or suspicious amount

        boolean[][] dp = new boolean[n + 1][target + 1];

        for (int i = 0; i <= n; i++) dp[i][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int sum = 0; sum <= target; sum++) {
                if (arr[i - 1] <= sum)
                    dp[i][sum] = dp[i - 1][sum] || dp[i - 1][sum - arr[i - 1]];
                else
                    dp[i][sum] = dp[i - 1][sum];
            }
        }

        if (dp[n][target]) {
            System.out.println("Yes, subset found:");
            int sum = target;
            for (int i = n; i > 0 && sum > 0; i--) {
                if (!dp[i - 1][sum]) {
                    System.out.println("Item: " + arr[i - 1]);
                    sum -= arr[i - 1];
                }
            }
        } else {
            System.out.println("No such subset exists.");
        }
    }
}

