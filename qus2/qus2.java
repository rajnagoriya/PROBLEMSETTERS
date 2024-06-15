// ======== question 2 ==============
// You need to design a hash function for an array where each element is mapped to a unique integer value (hash value). The function should satisfy the following conditions:

// Every distinct element in the array should have a unique hash value.
// The hash function should have a time complexity of O(1) for computing the hash value of each element.

// ================ solution ==============================
import java.util.*;

public class qus2 {
    
    public static int[] getUniqueHashValues(int[] arr) {
        HashMap<Integer, Integer> hashValueMap = new HashMap<>();
        int currentHash = 1; // Starting hash value
        
        int[] hashValues = new int[arr.length];
        
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (!hashValueMap.containsKey(num)) {
                hashValueMap.put(num, currentHash);
                hashValues[i] = currentHash;
                currentHash++; // Increment hash for next unique number
            } else {
                hashValues[i] = hashValueMap.get(num);
            }
        }
        
        return hashValues;
    }
    
    public static void main(String[] args) {
        int[] arr = {10, 5, 6, 10, 3, 5};
        
        int[] hashValues = getUniqueHashValues(arr);
        
        System.out.println("Original Array: " + Arrays.toString(arr));
        System.out.println("Hash Values: " + Arrays.toString(hashValues));
    }
}

// =========== Test cases ==========================

// Input: [3, 1, 4, 1, 5, 9, 2, 6, 5, 3]
// Expected Output: [1, 2, 3, 2, 4, 5, 6, 7, 4, 1]

// Input: [7, 8, 9, 7, 8, 9]
// Expected Output: [1, 2, 3, 1, 2, 3]