import java.util.*;

public class RiskThresholdSearch {

    // 🔹 Linear Search (unsorted)
    public static int linearSearch(int[] arr, int target, int[] comp) {
        comp[0] = 0;

        for (int i = 0; i < arr.length; i++) {
            comp[0]++;
            if (arr[i] == target) return i;
        }
        return -1;
    }

    // 🔹 Binary Search (Exact Match)
    public static int binarySearch(int[] arr, int target, int[] comp) {
        int low = 0, high = arr.length - 1;
        comp[0] = 0;

        while (low <= high) {
            comp[0]++;
            int mid = (low + high) / 2;

            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    // 🔹 Floor (largest ≤ target)
    public static int floor(int[] arr, int target, int[] comp) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            comp[0]++;
            int mid = (low + high) / 2;

            if (arr[mid] <= target) {
                result = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // 🔹 Ceiling (smallest ≥ target)
    public static int ceiling(int[] arr, int target, int[] comp) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            comp[0]++;
            int mid = (low + high) / 2;

            if (arr[mid] >= target) {
                result = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    // 🔹 Lower Bound (first index ≥ target)
    public static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] < target) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    // 🔹 Upper Bound (first index > target)
    public static int upperBound(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] <= target) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    // 🔹 Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of risk bands: ");
        int n = sc.nextInt();

        int[] risks = new int[n];

        System.out.println("Enter risk values (sorted or unsorted):");
        for (int i = 0; i < n; i++) {
            risks[i] = sc.nextInt();
        }

        System.out.print("\nEnter target threshold: ");
        int target = sc.nextInt();

        int[] linearComp = new int[1];
        int[] binaryComp = new int[1];

        // 🔸 Linear Search (unsorted)
        int linIndex = linearSearch(risks, target, linearComp);
        System.out.println("\nLinear Search:");
        System.out.println("Index: " + linIndex);
        System.out.println("Comparisons: " + linearComp[0]);
        System.out.println("Time Complexity: O(n)");

        // 🔸 Sort for Binary Search
        Arrays.sort(risks);
        System.out.println("\nSorted Risks: " + Arrays.toString(risks));

        // 🔸 Binary Search
        int binIndex = binarySearch(risks, target, binaryComp);
        System.out.println("\nBinary Search:");
        System.out.println("Index: " + binIndex);
        System.out.println("Comparisons: " + binaryComp[0]);

        // 🔸 Floor & Ceiling
        int floorVal = floor(risks, target, binaryComp);
        int ceilVal = ceiling(risks, target, binaryComp);

        System.out.println("\nFloor (≤ target): " + floorVal);
        System.out.println("Ceiling (≥ target): " + ceilVal);

        // 🔸 Lower & Upper Bound
        int lb = lowerBound(risks, target);
        int ub = upperBound(risks, target);

        System.out.println("\nLower Bound index: " + lb);
        System.out.println("Upper Bound index: " + ub);

        sc.close();
    }
}
