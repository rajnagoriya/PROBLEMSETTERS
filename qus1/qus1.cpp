// ======== question 1 ==============
// You are given an array of integers where each integer represents a specific item. Your task is to determine the smallest subarray (continuous segment) that contains at least one of each unique item in the array.

// Write a function smallestSubarrayWithAllUniqueItems that takes an array of integers as input and returns the starting and ending indices (0-based) of the smallest subarray that contains all unique items. If there are multiple such subarrays, return the one with the smallest starting index. If the input array is empty, return [-1, -1].

// ----------------------------------------------------------------------------------------
// ========= solution cpp =====================

#include <iostream>
#include <vector>
#include <unordered_map>
#include <unordered_set>
#include <climits> 

using namespace std;

vector<int> smallestSubarrayWithAllUniqueItems(vector<int>& arr) {
    if (arr.empty()) return {-1, -1};
    
    unordered_set<int> unique_items(arr.begin(), arr.end());
    unordered_map<int, int> item_count;
    
    int total_unique = unique_items.size();
    int start = 0, min_length = INT_MAX, min_start = -1; 
    int current_unique_count = 0;

    for (int end = 0; end < arr.size(); end++) {
        if (item_count[arr[end]] == 0) {
            current_unique_count++;
        }
        item_count[arr[end]]++;
        
        while (current_unique_count == total_unique) {
            if (end - start + 1 < min_length) {
                min_length = end - start + 1;
                min_start = start;
            }
            item_count[arr[start]]--;
            if (item_count[arr[start]] == 0) {
                current_unique_count--;
            }
            start++;
        }
    }
    
    if (min_start == -1) return {-1, -1};
    return {min_start, min_start + min_length - 1};
}

// Test cases
int main() {
    vector<vector<int>> test_cases = {
         {1, 2, 2, 1, 3, 4, 2, 3},
            {1, 1, 1, 1},
            {},
            {1, 2, 3, 4, 5, 6},
            {1, 2, 3, 4, 2, 2, 3, 4, 5, 6}
    };
    vector<vector<int>> expected_results = {
        {2, 5},
            {0, 0},
            {-1, -1},
            {0, 5},
            {0, 9}
    };

    for (int i = 0; i < test_cases.size(); i++) {
        vector<int> result = smallestSubarrayWithAllUniqueItems(test_cases[i]);
        cout << "Test case " << i + 1 << ": ";
        if (result == expected_results[i]) {
            cout << "Passed" << endl;
        } else {
            cout << "Failed" << endl;
            cout << "Expected: [" << expected_results[i][0] << ", " << expected_results[i][1] << "], "
                 << "Got: [" << result[0] << ", " << result[1] << "]" << endl;
        }
    }

    return 0;
}

