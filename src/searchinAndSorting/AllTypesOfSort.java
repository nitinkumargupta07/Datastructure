package searchinAndSorting;

import java.util.Arrays;
import java.util.Comparator;

public class AllTypesOfSort {
	static int[] unsortedaray = { 23, 54, 9, 12, 34, 76, 89, 99, 43 };
	public static final int RUN = 32;

	public static void main(String[] args) {
		System.out.println(Arrays.toString(unsortedaray));
		// bubbleSort();
		// selectionSort();
		// insertionSort();
		// quickSort();
		// mergeSort();
		// int maxValue =
		// Arrays.stream(unsortedaray).boxed().max(Comparator.naturalOrder()).get();
		/// int[] sorted_sequence = bucketSort(unsortedaray, maxValue);
		// System.out.println(Arrays.toString(sorted_sequence));
		// heapSort(unsortedaray);
		// radixSort(unsortedaray);
		///countingSort(unsortedaray, Arrays.stream(unsortedaray).boxed().max(Comparator.naturalOrder()).get());
		/// int n = sizeof(unsortedaray)/sizeof(unsortedaray[0]);  
		timSort(unsortedaray, 100 );
		System.out.println(Arrays.toString(unsortedaray));
	}

	// Timsort code start
	static void insertionSort(int arr[], int left, int right) {
		for (int i = left + 1; i <= right; i++) {
			int temp = arr[i];
			int j = i - 1;
			while (arr[j] > temp && j >= left) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = temp;
		}
	}

	static void timSort(int arr[], int n) {
		for (int i = 0; i < n; i += RUN)
			insertionSort(arr, i, Math.min((i + 31), (n - 1)));

		// start merging from size RUN (or 32). It will merge
		// to form size 64, then 128, 256 and so on ....
		for (int size = RUN; size < n; size = 2 * size) {
			// pick starting point of left sub array. We
			// are going to merge arr[left..left+size-1]
			// and arr[left+size, left+2*size-1]
			// After every merge, we increase left by 2*size
			for (int left = 0; left < n; left += 2 * size) {
				// find ending point of left sub array
				// mid+1 is starting point of right sub array
				int mid = left + size - 1;
				int right = Math.min((left + 2 * size - 1), (n - 1));

				// merge sub array arr[left.....mid] &
				// arr[mid+1....right]
				sortedMerge(arr, left, mid, right);
			}
		}
	}

	// Timsort code end
	public static void countingSort(int[] input, int k) {
		int counter[] = new int[k + 1]; // fill buckets
		for (int i : input) {
			counter[i]++;
		}
		int ndx = 0;
		for (int i = 0; i < counter.length; i++) {
			while (0 < counter[i]) {
				input[ndx++] = i;
				counter[i]--;
			}
		}
	}

	private static void radixSort(int[] unsortedaray2) {
		int n = unsortedaray2.length;
		int m = Arrays.stream(unsortedaray).boxed().max(Comparator.naturalOrder()).get();
		for (int exp = 1; m / exp > 0; exp *= 10)
			countSort(unsortedaray, n, exp);
	}

	private static void countSort(int[] unsortedaray2, int n, int exp) {
		int output[] = new int[n]; // output array
		int i;
		int count[] = new int[10];
		/// Arrays.fill(count, 0);

		// Store count of occurrences in count[]
		for (i = 0; i < n; i++)
			count[(unsortedaray2[i] / exp) % 10]++;

		// Change count[i] so that count[i] now contains
		// actual position of this digit in output[]
		for (i = 1; i < 10; i++)
			count[i] += count[i - 1];

		// Build the output array
		for (i = n - 1; i >= 0; i--) {
			output[count[(unsortedaray2[i] / exp) % 10] - 1] = unsortedaray2[i];
			count[(unsortedaray2[i] / exp) % 10]--;
		}

		// Copy the output array to arr[], so that arr[] now
		// contains sorted numbers according to curent digit
		for (i = 0; i < n; i++)
			unsortedaray2[i] = output[i];

	}

	static public void heapSort(int arr[]) {
		int n = arr.length;

		// Build heap (rearrange array)
		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(arr, n, i);

		// One by one extract an element from heap
		for (int i = n - 1; i >= 0; i--) {
			// Move current root to end
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			// call max heapify on the reduced heap
			heapify(arr, i, 0);
		}
	}

	// To heapify a subtree rooted with node i which is
	// an index in arr[]. n is size of heap
	static void heapify(int arr[], int n, int i) {
		int largest = i; // Initialize largest as root
		int l = 2 * i + 1; // left = 2*i + 1
		int r = 2 * i + 2; // right = 2*i + 2

		// If left child is larger than root
		if (l < n && arr[l] > arr[largest])
			largest = l;

		// If right child is larger than largest so far
		if (r < n && arr[r] > arr[largest])
			largest = r;

		// If largest is not root
		if (largest != i) {
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;

			// Recursively heapify the affected sub-tree
			heapify(arr, n, largest);
		}
	}

	private static int[] bucketSort(int[] unsortedaray2, int maxValue) {
		int[] Bucket = new int[maxValue + 1];
		int[] sorted_sequence = new int[unsortedaray2.length];
		for (int i = 0; i < unsortedaray2.length; i++)
			Bucket[unsortedaray2[i]]++;
		int outPos = 0;
		for (int i = 0; i < Bucket.length; i++)
			for (int j = 0; j < Bucket[i]; j++)
				sorted_sequence[outPos++] = i;
		return sorted_sequence;

	}

	private static void mergeSort() {
		mergeSort(unsortedaray, 0, unsortedaray.length - 1);
	}

	private static void mergeSort(int[] unsortedaray2, int low, int high) {
		if (low < high) {
			int mid = (low + high) / 2;
			mergeSort(unsortedaray2, low, mid);
			mergeSort(unsortedaray2, mid + 1, high);
			sortedMerge(unsortedaray2, low, mid, high);
		}
	}

	private static void sortedMerge(int[] unsortedaray2, int beg, int mid, int end) {
		int l = mid - beg + 1;
		int r = end - mid;

		int LeftArray[] = new int[l];
		int RightArray[] = new int[r];

		for (int i = 0; i < l; ++i)
			LeftArray[i] = unsortedaray2[beg + i];

		for (int j = 0; j < r; ++j)
			RightArray[j] = unsortedaray2[mid + 1 + j];

		int i = 0, j = 0;
		int k = beg;
		while (i < l && j < r) {
			if (LeftArray[i] <= RightArray[j]) {
				unsortedaray2[k] = LeftArray[i];
				i++;
			} else {
				unsortedaray2[k] = RightArray[j];
				j++;
			}
			k++;
		}
		while (i < l) {
			unsortedaray2[k] = LeftArray[i];
			i++;
			k++;
		}

		while (j < r) {
			unsortedaray2[k] = RightArray[j];
			j++;
			k++;
		}

	}

	private static void quickSort() {
		// quickSort(unsortedaray, 0, unsortedaray.length - 1);
		quickSort(0, unsortedaray.length - 1);
	}

	private static void quickSort(int low, int high) {
		int i = low;
		int j = high;
		int pivot = unsortedaray[(low + high) / 2];
		while (i <= j) {
			while (unsortedaray[i] < pivot) {
				i++;
			}
			while (unsortedaray[j] > pivot) {
				j--;
			}
			if (i <= j) {
				exchangeNumber(i, j);
				i++;
				j--;
			}
			if (low < j)
				quickSort(low, j);
			if (i < high)
				quickSort(i, high);
		}
	}

	private static void exchangeNumber(int i, int j) {
		int temp = unsortedaray[i];
		unsortedaray[i] = unsortedaray[j];
		unsortedaray[j] = temp;
	}

	private static void quickSort(int[] unsortedaray2, int low, int high) {
		int temp = 0;
		if (low < high) {
			temp = partition(unsortedaray2, low, high);
			quickSort(unsortedaray2, low, temp - 1);
			quickSort(unsortedaray2, temp + 1, high);
		}
	}

	static int partition(int arr[], int low, int high) {
		int pivot = arr[high];
		int i = (low - 1); // index of smaller element
		for (int j = low; j < high; j++) {
			if (arr[j] <= pivot) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		// swap arr[i+1] and arr[high] (or pivot)
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;
		return i + 1;
	}

	private static void insertionSort() {
		for (int i = 1; i < unsortedaray.length; i++) {
			int j = i - 1;
			for (; j >= 0; j--) {
				if (unsortedaray[j] > unsortedaray[j + 1]) {
					int temp = unsortedaray[j + 1];
					unsortedaray[j + 1] = unsortedaray[j];
					unsortedaray[j] = temp;
				}
			}
		}
	}

	private static void selectionSort() {
		for (int i = 0; i < unsortedaray.length; i++) {
			int min_idx = i;
			for (int j = i + 1; j < unsortedaray.length; j++)
				if (unsortedaray[min_idx] > unsortedaray[j])
					min_idx = j;
			int temp = unsortedaray[min_idx];
			unsortedaray[min_idx] = unsortedaray[i];
			unsortedaray[i] = temp;
		}
	}

	private static void bubbleSort() {
		for (int i = 0; i < unsortedaray.length; i++) {
			for (int j = 0; j < unsortedaray.length - 1; j++) {
				if (unsortedaray[j] > unsortedaray[j + 1]) {
					int temp = unsortedaray[j + 1];
					unsortedaray[j + 1] = unsortedaray[j];
					unsortedaray[j] = temp;
				}
			}
		}
	}
}
