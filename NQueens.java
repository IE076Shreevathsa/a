import java.util.*;

public class NQueens {
    static int count = 0;

    static void solve(int[] board, int row, int n) {
        if (row == n) {
            printBoard(board, n);
            count++;
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                board[row] = col;
                solve(board, row + 1, n);
            }
        }
    }

    static boolean isSafe(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(i - row) == Math.abs(board[i] - col))
                return false;
        }
        return true;
    }

    static void printBoard(int[] board, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print((board[i] == j ? "Q " : ". "));
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter N (size of board): ");
        int n = sc.nextInt();

        int[] board = new int[n];
        solve(board, 0, n);

        System.out.println("Total solutions: " + count);
    }
}

