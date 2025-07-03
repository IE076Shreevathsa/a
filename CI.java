import java.util.*;

public class CI {

    public static int mergeSort(int[] arr, int l, int r) {
        int count = 0;
        if (l < r) {
            int mid = (l + r) / 2;
            count += mergeSort(arr, l, mid);
            count += mergeSort(arr, mid + 1, r);
            count += merge(arr, l, mid, r);
        }
        return count;
    }

    public static int merge(int[] arr, int l, int mid, int r) {
        int n1 = mid - l + 1;
        int n2 = r - mid;
        int[] lArr = new int[n1];
        int[] rArr = new int[n2];

        for (int i = 0; i < n1; i++) lArr[i] = arr[l + i];
        for (int i = 0; i < n2; i++) rArr[i] = arr[mid + 1 + i];

        int inversions = 0, i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {
            if (lArr[i] <= rArr[j]) {
                arr[k++] = lArr[i++];
            } else {
                arr[k++] = rArr[j++];
                inversions += (n1 - i);
            }
        }

        while (i < n1) arr[k++] = lArr[i++];
        while (j < n2) arr[k++] = rArr[j++];

        return inversions;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] users = new int[3][8];
        int[] inversions = new int[3];

        for (int u = 0; u < 3; u++) {
            System.out.println("Enter the playlist of User " + (u + 1) + ":");
            for (int j = 0; j < 8; j++) {
                users[u][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < 3; i++) {
            inversions[i] = mergeSort(users[i].clone(), 0, 7);
        }

        System.out.println("\nInversion counts:");
        for (int i = 0; i < 3; i++) {
            System.out.println("User " + (i + 1) + ": " + inversions[i]);
        }

        System.out.println("\nRecommendations:");
        for (int i = 0; i < 3; i++) {
            int minDiff = Integer.MAX_VALUE;
            int rec = -1;
            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    int diff = Math.abs(inversions[i] - inversions[j]);
                    if (diff < minDiff) {
                        minDiff = diff;
                        rec = j;
                    }
                }
            }
            System.out.println("User " + (i + 1) + " should try playlist of User " + (rec + 1));
        }
    }
}

