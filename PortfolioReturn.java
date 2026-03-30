import java.util.*;

// 🔹 Asset Class
class Asset {
    String name;
    double returnRate;
    double volatility;

    Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return name + ":" + returnRate + "% (vol=" + volatility + ")";
    }
}

// 🔹 Main Class
public class PortfolioSorter {

    // 🔸 Merge Sort (Ascending returnRate - Stable)
    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public static void merge(Asset[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        // Stable: <= keeps original order for ties
        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // 🔸 Quick Sort (Descending returnRate + Asc volatility)
    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = medianOfThree(arr, low, high);
            swap(arr, pivotIndex, high); // move pivot to end

            int pi = partition(arr, low, high);

            // Hybrid optimization: small partitions → insertion sort
            if (pi - low < 10)
                insertionSort(arr, low, pi - 1);
            else
                quickSort(arr, low, pi - 1);

            if (high - pi < 10)
                insertionSort(arr, pi + 1, high);
            else
                quickSort(arr, pi + 1, high);
        }
    }

    // 🔸 Partition (Lomuto)
    public static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (arr[j].returnRate > pivot.returnRate ||
               (arr[j].returnRate == pivot.returnRate &&
                arr[j].volatility < pivot.volatility)) {

                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // 🔸 Median-of-3 Pivot Selection
    public static int medianOfThree(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;

        double a = arr[low].returnRate;
        double b = arr[mid].returnRate;
        double c = arr[high].returnRate;

        if ((a > b && a < c) || (a < b && a > c)) return low;
        if ((b > a && b < c) || (b < a && b > c)) return mid;
        return high;
    }

    // 🔸 Insertion Sort (for small partitions)
    public static void insertionSort(Asset[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Asset key = arr[i];
            int j = i - 1;

            while (j >= low &&
                  (arr[j].returnRate < key.returnRate ||
                  (arr[j].returnRate == key.returnRate &&
                   arr[j].volatility > key.volatility))) {

                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    // 🔸 Swap Utility
    public static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 🔹 Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of assets: ");
        int n = sc.nextInt();
        sc.nextLine();

        Asset[] assets = new Asset[n];

        // 🔸 Input
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter asset " + (i + 1));

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Return Rate (%): ");
            double ret = sc.nextDouble();

            System.out.print("Volatility: ");
            double vol = sc.nextDouble();
            sc.nextLine();

            assets[i] = new Asset(name, ret, vol);
        }

        // 🔸 Copy arrays
        Asset[] mergeArray = assets.clone();
        Asset[] quickArray = assets.clone();

        // 🔸 Merge Sort (Ascending)
        mergeSort(mergeArray, 0, n - 1);
        System.out.println("\nMerge Sort (Ascending Return Rate):");
        System.out.println(Arrays.toString(mergeArray));

        // 🔸 Quick Sort (Descending + Volatility)
        quickSort(quickArray, 0, n - 1);
        System.out.println("\nQuick Sort (Desc Return + Low Volatility Preferred):");
        System.out.println(Arrays.toString(quickArray));

        sc.close();
    }
}
