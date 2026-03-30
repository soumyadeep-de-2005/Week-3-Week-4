import java.util.*;

// 🔹 Transaction Class
class Transaction {
    String id;
    double fee;
    String timestamp;

    Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

// 🔹 Main Class
public class TransactionSorter {

    // 🔸 Bubble Sort (for ≤100 transactions)
    public static void bubbleSort(ArrayList<Transaction> list) {
        int n = list.size();
        int passes = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }

            // Early termination if already sorted
            if (!swapped) break;
        }

        System.out.println("\nBubble Sort (by fee):");
        System.out.println(list);
        System.out.println("Passes = " + passes + ", Swaps = " + swaps);
    }

    // 🔸 Insertion Sort (for 100–1000 transactions)
    public static void insertionSort(ArrayList<Transaction> list) {
        int n = list.size();

        for (int i = 1; i < n; i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            // Sort by fee, then timestamp (stable)
            while (j >= 0 &&
                  (list.get(j).fee > key.fee ||
                  (list.get(j).fee == key.fee &&
                   list.get(j).timestamp.compareTo(key.timestamp) > 0))) {

                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }

        System.out.println("\nInsertion Sort (fee + timestamp):");
        System.out.println(list);
    }

    // 🔸 Detect High Fee Transactions (>50)
    public static void detectHighFees(ArrayList<Transaction> list) {
        System.out.println("\nHigh-fee outliers (>50):");

        boolean found = false;
        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("None");
        }
    }

    // 🔹 Main Function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Transaction> transactions = new ArrayList<>();

        System.out.print("Enter number of transactions: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        // 🔸 Input transactions
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for transaction " + (i + 1));

            System.out.print("ID: ");
            String id = sc.nextLine();

            System.out.print("Fee: ");
            double fee = sc.nextDouble();
            sc.nextLine();

            System.out.print("Timestamp (HH:MM): ");
            String ts = sc.nextLine();

            transactions.add(new Transaction(id, fee, ts));
        }

        // 🔸 Choose sorting algorithm
        if (n <= 100) {
            bubbleSort(transactions);
        } else if (n <= 1000) {
            insertionSort(transactions);
        } else {
            System.out.println("\nDataset too large! Use advanced sorting (Merge/Quick sort).");
        }

        // 🔸 Detect high-fee transactions
        detectHighFees(transactions);

        sc.close();
    }
}
