import java.util.*;

// 🔹 Client Class
class Client {
    String name;
    int riskScore;
    double accountBalance;

    Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + ":" + riskScore + "(₹" + accountBalance + ")";
    }
}

// 🔹 Main Class
public class ClientRiskSorter {

    // 🔸 Bubble Sort (Ascending riskScore)
    public static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        System.out.println("\nBubble Sort Process (Swaps visualization):");

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    // swap
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaps++;
                    swapped = true;

                    // Show each swap
                    System.out.println("Swap " + swaps + ": " + Arrays.toString(arr));
                }
            }

            if (!swapped) break; // early stop
        }

        System.out.println("\nFinal Bubble Sorted (Ascending):");
        System.out.println(Arrays.toString(arr));
        System.out.println("Total Swaps: " + swaps);
    }

    // 🔸 Insertion Sort (Descending riskScore + accountBalance)
    public static void insertionSort(Client[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            Client key = arr[i];
            int j = i - 1;

            // Descending by riskScore, if equal then higher balance first
            while (j >= 0 &&
                  (arr[j].riskScore < key.riskScore ||
                  (arr[j].riskScore == key.riskScore &&
                   arr[j].accountBalance < key.accountBalance))) {

                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        System.out.println("\nInsertion Sort (Descending riskScore + balance):");
        System.out.println(Arrays.toString(arr));
    }

    // 🔸 Top 10 Highest Risk Clients
    public static void topHighRisk(Client[] arr) {
        System.out.println("\nTop High-Risk Clients:");

        int limit = Math.min(10, arr.length);

        for (int i = 0; i < limit; i++) {
            System.out.println((i + 1) + ". " + arr[i]);
        }
    }

    // 🔹 Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of clients: ");
        int n = sc.nextInt();
        sc.nextLine();

        Client[] clients = new Client[n];

        // 🔸 Input
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for client " + (i + 1));

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Risk Score: ");
            int risk = sc.nextInt();

            System.out.print("Account Balance: ");
            double balance = sc.nextDouble();
            sc.nextLine();

            clients[i] = new Client(name, risk, balance);
        }

        // 🔸 Make copies for both sorts
        Client[] bubbleArray = clients.clone();
        Client[] insertionArray = clients.clone();

        // 🔸 Apply sorting
        bubbleSort(bubbleArray);
        insertionSort(insertionArray);

        // 🔸 Top 10 highest risk
        topHighRisk(insertionArray);

        sc.close();
    }
}
