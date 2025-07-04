import java.util.*;
public class WeightedIntervalSchedulingAlgo {
static class Job {
int start, finish, value;
Job(int start, int finish, int value) {
this.start = start;
this.finish = finish;
this.value = value;
}
}
// Binary search to find the last non-conflicting job
static int lastNonConflict(Job[] jobs, int index) {
int low = 0, high = index - 1;
while (low <= high) {
int mid = (low + high) / 2;
if (jobs[mid].finish <= jobs[index].start) {
if (mid + 1 <= high && jobs[mid + 1].finish <= jobs[index].start)
low = mid + 1;
else
return mid;
} else {
high = mid - 1;
}
}
return -1;
}
// Top-down DP with memoization
static int maxProfit(Job[] jobs, int i, int[] memo) {
if (i < 0) return 0;
if (memo[i] != -1) return memo[i];
int include = jobs[i].value + maxProfit(jobs, lastNonConflict(jobs, i), memo);
int exclude = maxProfit(jobs, i - 1, memo);
memo[i] = Math.max(include, exclude);
return memo[i];
}public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter number of drama requests: ");
int n = sc.nextInt();
Job[] jobs = new Job[n];
for (int i = 0; i < n; i++) {
System.out.print("Enter Start time, Finish time, and Value for job " + (i +
1) + ": ");
jobs[i] = new Job(sc.nextInt(), sc.nextInt(), sc.nextInt());
}
Arrays.sort(jobs, Comparator.comparingInt(j -> j.finish));
int[] memo = new int[n];
Arrays.fill(memo, -1);
System.out.println("Maximum Profit of non overlapping job's : " +
maxProfit(jobs, n - 1, memo));
}
}
