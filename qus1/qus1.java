// ======== question 1 ==============
// You are given an array of integers where each integer represents a specific item. Your task is to determine the smallest subarray (continuous segment) that contains at least one of each unique item in the array.

// Write a function smallestSubarrayWithAllUniqueItems that takes an array of integers as input and returns the starting and ending indices (0-based) of the smallest subarray that contains all unique items. If there are multiple such subarrays, return the one with the smallest starting index. If the input array is empty, return [-1, -1].

// ----------------------------------------------------------------------------------------
// ========= solution java =====================

import java.util.*;

public class qus1 {
    public static int[] smallestSubarrayWithAllUniqueItems(int[] arr) {
        if (arr.length == 0) return new int[]{-1, -1};
        
        Set<Integer> uniqueItems = new HashSet<>();
        for (int num : arr) {
            uniqueItems.add(num);
        }
        
        Map<Integer, Integer> itemCount = new HashMap<>();
        int totalUnique = uniqueItems.size();
        int start = 0, minLength = Integer.MAX_VALUE, minStart = -1;
        int currentUniqueCount = 0;

        for (int end = 0; end < arr.length; end++) {
            itemCount.put(arr[end], itemCount.getOrDefault(arr[end], 0) + 1);
            if (itemCount.get(arr[end]) == 1) {
                currentUniqueCount++;
            }

            while (currentUniqueCount == totalUnique) {
                if (end - start + 1 < minLength) {
                    minLength = end - start + 1;
                    minStart = start;
                }
                itemCount.put(arr[start], itemCount.get(arr[start]) - 1);
                if (itemCount.get(arr[start]) == 0) {
                    currentUniqueCount--;
                }
                start++;
            }
        }
        
        if (minStart == -1) return new int[]{-1, -1};
        return new int[]{minStart, minStart + minLength - 1};
    }

    // Test cases
    public static void main(String[] args) {
        int[][] testCases = {
            {1, 2, 2, 1, 3, 4, 2, 3},
            {1, 1, 1, 1},
            {},
            {1, 2, 3, 4, 5, 6},
            {1, 2, 3, 4, 2, 2, 3, 4, 5, 6}
        };
        int[][] expectedResults = {
            {2, 5},
            {0, 0},
            {-1, -1},
            {0, 5},
            {0, 9}
        };

        for (int i = 0; i < testCases.length; i++) {
            int[] result = smallestSubarrayWithAllUniqueItems(testCases[i]);
            System.out.print("Test case " + (i + 1) + ": ");
            if (Arrays.equals(result, expectedResults[i])) {
                System.out.println("Passed");
            } else {
                System.out.println("Failed");
                System.out.println("Expected: [" + expectedResults[i][0] + ", " + expectedResults[i][1] + "], "
                        + "Got: [" + result[0] + ", " + result[1] + "]");
            }
        }
    }
}

