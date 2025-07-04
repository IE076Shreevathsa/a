import java.util.*;
public class SimpleGaleShapley {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter number of men/women: ");
int n = sc.nextInt();
sc.nextLine(); // Consume newline
String[] men = new String[n];
String[] women = new String[n];
int[][] menPref = new int[n][n];
int[][] womenRank = new int[n][n];
// Read men names
System.out.println("\nEnter men names (e.g., A B C):");
String[] manNames = sc.nextLine().trim().split(" ");
for (int i = 0; i < n; i++) {
men[i] = manNames[i];
}
// Read women names
System.out.println("\nEnter women names (e.g., V W X):");
String[] womanNames = sc.nextLine().trim().split(" ");
for (int i = 0; i < n; i++) {
women[i] = womanNames[i];
}
// Read men's preference lists
System.out.println("\nEnter preference list for each man (as woman letters):");
for (int i = 0; i < n; i++) {
System.out.print("Preferences for " + men[i] + ": ");
String[] prefs = sc.nextLine().trim().split(" ");
for (int j = 0; j < n; j++) {
for (int k = 0; k < n; k++) {
if (prefs[j].equals(women[k])) {
menPref[i][j] = k; // store index of woman
}
}
}
}
// Read women's preference lists and convert to rankings
System.out.println("\nEnter preference list for each woman (as man letters):");
for (int i = 0; i < n; i++) {
System.out.print("Preferences for " + women[i] + ": ");
String[] prefs = sc.nextLine().trim().split(" ");
for (int j = 0; j < n; j++) {
for (int k = 0; k < n; k++) {
if (prefs[j].equals(men[k])) {
womenRank[i][k] = j; // ranking of man k for woman i
}}
}
}
int[] womenPartner = new int[n]; // woman[i] is engaged to man womenPartner[i]
boolean[] manFree = new boolean[n];
int[] nextProposal = new int[n];
Arrays.fill(womenPartner, -1);
Arrays.fill(manFree, true);
int freeMen = n;
// Gale-Shapley Algorithm
while (freeMen > 0) {
for (int m = 0; m < n; m++) {
if (manFree[m]) {
int w = menPref[m][nextProposal[m]++]; // next woman index
if (womenPartner[w] == -1) {
womenPartner[w] = m;
manFree[m] = false;
freeMen--;
} else {
int current = womenPartner[w];
if (womenRank[w][m] < womenRank[w][current]) {
womenPartner[w] = m;
manFree[m] = false;
manFree[current] = true;
}
}
}
}
}
// Print stable matches
System.out.println("\nStable Matches:");
for (int w = 0; w < n; w++) {
System.out.println(women[w] + " is matched with " + men[womenPartner[w]]);
}
sc.close();
}
}

