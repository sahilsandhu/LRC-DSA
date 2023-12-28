class Main{
    // Bubble Sort
    public static void bubbleSort(int[] arr) {
      // In the bubble sort we always try to move the maximum one to the end of the array from
      // begininng
      // Thats the reason after each iteration we are aware of the face that the last element of the
      // array is at its correct position
      int n = arr.length;
      for(int i=1; i<n; i++){
          for(int j=0; j<n-i; j++){
              if(arr[j] > arr[j+1]){
                  swap(arr, j+1, j);
              }
          }
      }
  }

  public static void selectionSort(int[] arr) {
      // Here in the Selection Sort we select the minimum element from the right side of the
      // Array, if there is any element [min] who is smaller the ith element than we swap it 
      // with the ith element with the min
      // Hence here we try to find the smallest element in the begininng of the array
    int n = arr.length;
    for(int i=0; i<n; i++){
        int idx = -1;
        int min = arr[i];
        for(int j=i+1; j<n; j++){
            if(arr[j] < min){
                min = arr[j];
                idx = j;
            }
        }
        if(idx != -1){
            swap(arr, i, idx);
        }
    }

    // Merge Sort

    public static int[] mergeSort(int[] arr, int lo, int hi) {
    if(lo = hi){
        int[] arr = new int[1];
        ar[lo] = arr[lo];
        return ar;
    }
    int mid = (lo + hi) / 2;
    int[] left = mergeSort(arr, lo, mid)
    int[] right = mergeSort(arr, mid+1, hi);
    return mergeTwoSortedArrays(a, b);
  }

  //used for merging two sorted arrays
  public static int[] mergeTwoSortedArrays(int[] a, int[] b){
    System.out.println("Merging these two arrays ");
    System.out.print("left array -> ");
    print(a);
    System.out.print("right array -> ");
    print(b);
    int i = 0, j =0, k = 0;
    int[] ans = new int[a.length + b.length];
    while(i < a.length && j < b.length){
        if(a[i] <= b[j]){
          ans[k] = a[i];
          i++;
          k++;
        }else{
          ans[k] = b[j];
          j++;
          k++;
        }
    }

    while(i < a.length){
      ans[k] = a[i];
      k++;
      i++;
    }

    while(j < b.length){
      ans[k] = b[j];
      k++;
      j++;
    }
    
    return ans;
  }



  // Partition An Array
  // Here we need to seperate all the greater elements than pivot to the left and all the greater elements to the 
  // right of pivot

  public static void partition(int[] arr, int pivot){
    //write your code here
    // Here the range of [0, j] -> Smaller element
    // [j+1, i] -> Greater element than pivot 
    // [i+1, n] -> Unknown element
    int i=0, j=0, n = arr.length;
    while(i<n){
        if(arr[i] <= pivot){
            swap(arr, i, j);
            i++;
            j++;
        }else{
            i++;
        }
    }
  }

  // QuickSort -> Sorting the array

  public static void quickSort(int[] arr, int lo, int hi) {
    if (lo > hi) {
      return;
    }
    int pivot = arr[hi];
    int pidx = partition(arr, pivot, lo, hi);
    quickSort(arr, lo, pidx - 1);
    quickSort(arr, pidx + 1, hi);
  }

  public static int partition(int[] arr, int pivot, int lo, int hi) {
    System.out.println("pivot -> " + pivot);
    int i = lo, j = lo;
    while (i <= hi) {
      if (arr[i] <= pivot) {
        swap(arr, i, j);
        i++;
        j++;
      } else {
        i++;
      }
    }
    System.out.println("pivot index -> " + (j - 1));
    return (j - 1);
  }


  // Quick Select : Finding the smallest kth element array using QuickSelect 
  public static int quickSelect(int[] arr, int lo, int hi, int k) {
      int pivot = arr[hi];
      int idx = partition(arr, pivot, lo, hi);
      if(idx == k){
          return arr[idx];
      }
      if(k < idx){
          return quickSelect(arr, lo, idx-1, k);
      }else if(k > idx){
          return quickSelect(arr, idx+1, hi, k);
      }
    return -1;
  }

  public static int partition(int[] arr, int pivot, int lo, int hi) {
    System.out.println("pivot -> " + pivot);
    int i = lo, j = lo;
    while (i <= hi) {
      if (arr[i] <= pivot) {
        swap(arr, i, j);
        i++;
        j++;
      } else {
        i++;
      }
    }
    System.out.println("pivot index -> " + (j - 1));
    return (j - 1);
  }


// Count Sort  :: This Sorting is used when the digits in the array are within the range and their values are repeated 
// In this case we use the Count Sort by making the frequency array and then making the Prefix sum array and 
// then filling the answer array on the basis of those values.

// Note : Why we need to do the prefix sum array : because it is a stable sorting algortihm that means each time,
// it maintains the ordering of element.

public static void countSort(int[] arr, int min, int max) {
    int range = max - min + 1;
    int[] ans = new int[arr.length];
    //make frequency arr
    int[] farr = new int[range];
    for(int i = 0 ; i < arr.length; i++){
      farr[arr[i] - min]++;
    }
    //convert it into prefix sum array
    for(int i = 1 ; i < farr.length; i++){
      farr[i] += farr[i - 1];
    }
    //stable sorting(filling ans array)
    for(int i = arr.length - 1; i >= 0; i--){
      int pos = farr[arr[i] - min] - 1;
      ans[pos] = arr[i];
      farr[arr[i] - min]--;
    }
    //filling original array with the help of ans array
    for(int i = 0 ; i < arr.length; i++){
      arr[i] = ans[i];
    }
  }

// Radix Sort : this sorting algortihm sort the data on the basis of the digits one by one's
// At first it sorts the one'th position elements then 10'th position elements then 100'th

public static void radixSort(int[] arr) {
      int max = Integer.MIN_VALUE;
      for(int val : arr){
          max = Math.max(max, val);
      }
      int exp = 1;
      while(max > 0){
          countSort(arr, exp);
          exp *= 10;
          max /= 10;
      }
  }
  
  public static void countSort(int[] arr, int exp) {
   int size = 10, n = arr.length;
   int[] ans = new int[n];
   // finding the count Array
   int[] count = new int[size];
   for(int i=0; i<n; i++){
       count[(arr[i]/exp)%10]++;
   }
   print(count);
   
   // Prefix Array
   int[] prefix = new int[size];
   for(int i=0; i<size; i++){
       if(i == 0){
           prefix[i] = count[i];
       }else{
           prefix[i] = (prefix[i-1] + count[i]) ; 
       }
   }
   print(prefix);
   
   // Filling the ans Array on the basis of the prefix Array
   for(int i=n-1; i>=0; i--){
       int idx = prefix[(arr[i]/exp)%10] - 1;
       ans[idx] = arr[i];
       prefix[(arr[i]/exp)%10]--;
   }
   print(ans);
  }


// Sort 0 - 1
public static void sort01(int[] arr){
      // 0 to j-1  ->  All Zeroes
    // j to i-1  ->  All Ones's
    // i to arr.length-1  ->  All unknowns
    
    int i = 0, j = 0;
    while (i < arr.length) {
      if (arr[i] == 0) {
        swap(arr, i, j);
        i++;
        j++;
      }else {
        i++;
      }
    }
  }


  // Sort 0 , 1, 2

  // Here the Ranges are defined in below way :::
  // [0, j-1] -> This range include the values of 0
  // [j, i-1] -> This Range include the values of 1
  // [i - k-1] -> This range contains all the unknown values
  // [k - n-1] -> This range includes the 2 

  public static void sort012(int[] arr){
    int i=0, j=0, k=arr.length-1;
    while(i<=k){
        if(arr[i] == 0){
            swap(arr, j, i);
            i++;
            j++;
        }else if(arr[i] == 1){
            i++;
        }else{
            swap(arr, i, k);
            k--;
        }
    }
    print(arr);
  }

 // Target Sum 

   public static void targetSumPair(int[] arr, int target){
    Arrays.sort(arr);
    int left = 0, right = arr.length - 1;
    while(left < right){
      if(arr[left] + arr[right] < target){
        left++;
      }else if(arr[left] + arr[right] == target){
        System.out.println(arr[left] + ", " + arr[right]);
        left++;
        right--;
      }else{
        right--;
      }
    }
  }

  // finding the minimum element in the rotates sorted array !!

  public static int findPivot(int[] arr) {
    int lo = 0;
    int hi = arr.length - 1;

    while (lo < hi) {
      int mid = (lo + hi) / 2;
      if (arr[mid] < arr[hi]) { // pivot will lie on left side
        hi = mid;
      } else if (arr[mid] > arr[hi]) { // pivot will lie on right side
        lo = mid + 1;
      } 
    }
    
    return arr[hi];
  }

}