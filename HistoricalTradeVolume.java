import java.util.*;

// 🔹 Trade Class
class Trade {
    String id;
    int volume;

    Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id + ":" + volume;
    }
}

// 🔹 Main Class
public class TradeAnalysis {

    // 🔸 Merge Sort (Ascending - Stable)
    public static void mergeSort(Trade[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public static void merge(Trade[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Trade[] L = new Trade[n1];
        Trade[] R = new Trade[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i].volume <= R[j].volume) { // stable
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1)
            arr[k++] = L[i++];

        while (j < n2)
            arr[k++] = R[j++];
    }

    // 🔸 Quick Sort (Descending)
    public static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Lomuto Partition (Descending)
    public static int partition(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume; // pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume > pivot) { // descending
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // 🔸 Merge Two Sorted Lists
    public static Trade[] mergeTwoSorted(Trade[] a, Trade[] b) {
        int n1 = a.length, n2 = b.length;
        Trade[] result = new Trade[n1 + n2];

        int i = 0, j = 0, k = 0;

        // Assuming both arrays are sorted ascending
        while (i < n1 && j < n2) {
            if (a[i].volume <= b[j].volume) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < n1) result[k++] = a[i++];
        while (j < n2) result[k++] = b[j++];

        return result;
    }

    // 🔸 Total Volume
    public static int totalVolume(Trade[] arr) {
        int sum = 0;
        for (Trade t : arr) {
            sum += t.volume;
        }
        return sum;
    }

    // 🔹 Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of trades: ");
        int n = sc.nextInt();
        sc.nextLine();

        Trade[] trades = new Trade[n];

        // 🔸 Input
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter trade " + (i + 1));

            System.out.print("Trade ID: ");
            String id = sc.nextLine();

            System.out.print("Volume: ");
            int vol = sc.nextInt();
            sc.nextLine();

            trades[i] = new Trade(id, vol);
        }

        // 🔸 Copy arrays
        Trade[] mergeArray = trades.clone();
        Trade[] quickArray = trades.clone();

        // 🔸 Merge Sort (Ascending)
        mergeSort(mergeArray, 0, n - 1);
        System.out.println("\nMerge Sort (Ascending):");
        System.out.println(Arrays.toString(mergeArray));

        // 🔸 Quick Sort (Descending)
        quickSort(quickArray, 0, n - 1);
        System.out.println("\nQuick Sort (Descending):");
        System.out.println(Arrays.toString(quickArray));

        // 🔸 Example: Merge two sorted halves
        int mid = n / 2;
        Trade[] morning = Arrays.copyOfRange(mergeArray, 0, mid);
        Trade[] afternoon = Arrays.copyOfRange(mergeArray, mid, n);

        Trade[] merged = mergeTwoSorted(morning, afternoon);

        System.out.println("\nMerged Morning + Afternoon:");
        System.out.println(Arrays.toString(merged));

        // 🔸 Total Volume
        int total = totalVolume(merged);
        System.out.println("\nTotal Volume: " + total);

        sc.close();
    }
}
