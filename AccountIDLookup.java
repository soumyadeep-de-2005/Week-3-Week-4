import java.util.*;

// 🔹 Main Class
public class AccountSearch {

    static int linearComparisons = 0;
    static int binaryComparisons = 0;

    // 🔸 Linear Search (First Occurrence)
    public static int linearFirst(String[] arr, String target) {
        linearComparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            linearComparisons++;
            if (arr[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    // 🔸 Linear Search (Last Occurrence)
    public static int linearLast(String[] arr, String target) {
        int index = -1;

        for (int i = 0; i < arr.length; i++) {
            linearComparisons++;
            if (arr[i].equals(target)) {
                index = i;
            }
        }
        return index;
    }

    // 🔸 Binary Search (Find any occurrence)
    public static int binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        binaryComparisons = 0;

        while (low <= high) {
            binaryComparisons++;
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                return mid;
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // 🔸 Count occurrences using Binary Search
    public static int countOccurrences(String[] arr, String target) {
        int first = firstOccurrence(arr, target);
        if (first == -1) return 0;

        int last = lastOccurrence(arr, target);
        return last - first + 1;
    }

    // 🔸 First occurrence using Binary Search
    public static int firstOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            binaryComparisons++;
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                high = mid - 1; // move left
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // 🔸 Last occurrence using Binary Search
    public static int lastOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            binaryComparisons++;
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                low = mid + 1; // move right
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // 🔹 Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of transaction logs: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[] logs = new String[n];

        // 🔸 Input
        for (int i = 0; i < n; i++) {
            System.out.print("Enter account ID " + (i + 1) + ": ");
            logs[i] = sc.nextLine();
        }

        System.out.print("\nEnter account ID to search: ");
        String target = sc.nextLine();

        // 🔸 Linear Search
        int first = linearFirst(logs, target);
        int last = linearLast(logs, target);

        System.out.println("\nLinear Search:");
        System.out.println("First occurrence index: " + first);
        System.out.println("Last occurrence index: " + last);
        System.out.println("Comparisons: " + linearComparisons);
        System.out.println("Time Complexity: O(n)");

        // 🔸 Sort before Binary Search
        Arrays.sort(logs);
        System.out.println("\nSorted Logs: " + Arrays.toString(logs));

        // 🔸 Binary Search
        int index = binarySearch(logs, target);
        int count = countOccurrences(logs, target);

        System.out.println("\nBinary Search:");
        System.out.println("Found at index: " + index);
        System.out.println("Total occurrences: " + count);
        System.out.println("Comparisons: " + binaryComparisons);
        System.out.println("Time Complexity: O(log n)");

        sc.close();
    }
}
