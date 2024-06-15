// ======== question 2 ==============
// You need to design a hash function for an array where each element is mapped to a unique integer value (hash value). The function should satisfy the following conditions:

// Every distinct element in the array should have a unique hash value.
// The hash function should have a time complexity of O(1) for computing the hash value of each element.

// ================ solution ==============================

#include <iostream>
#include <unordered_map>
#include <vector>

using namespace std;

vector<int> getUniqueHashValues(vector<int>& arr) {
    unordered_map<int, int> hashValueMap;
    int currentHash = 1; // Starting hash value
    
    vector<int> hashValues(arr.size());
    
    for (int i = 0; i < arr.size(); i++) {
        int num = arr[i];
        if (hashValueMap.find(num) == hashValueMap.end()) {
            hashValueMap[num] = currentHash;
            hashValues[i] = currentHash;
            currentHash++; // Increment hash for next unique number
        } else {
            hashValues[i] = hashValueMap[num];
        }
    }
    
    return hashValues;
}

int main() {
    vector<int> arr = {10, 5, 6, 10, 3, 5};
    
    vector<int> hashValues = getUniqueHashValues(arr);
    
    cout << "Original Array: ";
    for (int num : arr) {
        cout << num << " ";
    }
    cout << endl;
    
    cout << "Hash Values: ";
    for (int hash : hashValues) {
        cout << hash << " ";
    }
    cout << endl;
    
    return 0;
}

// =================Test cases =========================
//  Case 1: Array {3, 1, 4, 1, 5, 9, 2, 6, 5, 3}.
//  Case 2: Array {7, 8, 9, 7, 8, 9}.