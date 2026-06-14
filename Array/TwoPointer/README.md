# Two Pointer Pattern

## Definition

Two Pointer is an optimization technique that uses two indices to traverse an array simultaneously.

Instead of checking every possible combination using nested loops, pointers move intelligently based on conditions, helping reduce time complexity and improve performance.

Two Pointer is one of the most frequently used techniques in coding interviews and competitive programming.

---

## Common When To Use

Use Two Pointer when:

* Array is sorted.
* Need pair, triplet or quadruplet combinations.
* Need in-place modification.
* Need duplicate removal.
* Need opposite-end processing.
* Need counting of valid pairs/triplets.
* Need array partitioning.
* Need optimization from O(n²) to O(n).
* Need optimization from O(n³) to O(n²).

---

## General Template

```java
int left = 0;
int right = nums.length - 1;

while(left < right){

}
```

---

# 1. Opposite Direction Two Pointer

## Definition

One pointer starts from the beginning and another starts from the end.

Pointers move towards each other until a condition is satisfied.

---

## When To Use

* Sorted arrays.
* Pair sum problems.
* Palindrome checking.
* Largest area problems.
* Processing both ends of an array.

---

## Template

```java
int left = 0;
int right = nums.length - 1;

while(left < right){

    if(condition){
        left++;
    }
    else{
        right--;
    }
}
```

---

## Problems

### #167 Two Sum II

Find two numbers whose sum equals target.

Approach:

* Array is already sorted.
* Sum too small → left++.
* Sum too large → right--.
* Sum equals target → answer found.

---

### #11 Container With Most Water

Find maximum water that can be stored.

Approach:

* Calculate area using both ends.
* Move smaller height.
* Larger height may increase area.

---

### #977 Squares Of A Sorted Array

Return sorted squares.

Approach:

* Compare absolute values at both ends.
* Larger square goes at result end.
* Fill result array from right to left.

---

### #881 Boats To Save People

Find minimum boats required.

Approach:

* Sort the array.
* Pair smallest with largest.
* If pairing possible → move both pointers.
* Otherwise send largest alone.

---

### #42 Trapping Rain Water

Calculate trapped water.

Approach:

* Maintain leftMax and rightMax.
* Move smaller side.
* Calculate trapped water using current boundary.

---

# 2. Slow Fast Pointer

## Definition

One pointer reads data while another pointer builds the final answer.

Also called Read-Write Pointer technique.

---

## When To Use

* Remove duplicates.
* Remove elements.
* Move valid elements.
* Array compression.
* In-place modification.

---

## Template

```java
int write = 0;

for(int read = 0; read < nums.length; read++){

    if(valid){
        nums[write] = nums[read];
        write++;
    }
}
```

---

## Problems

### #26 Remove Duplicates From Sorted Array

Approach:

* Keep first occurrence.
* Copy only new values.
* write pointer stores valid elements.

---

### #27 Remove Element

Approach:

* Skip unwanted values.
* Copy remaining elements.
* Return valid length.

---

### #80 Remove Duplicates From Sorted Array II

Approach:

* Allow maximum two occurrences.
* Compare with nums[write - 2].
* Copy only valid values.

---

### #283 Move Zeroes

Approach:

* Move non-zero values forward.
* Fill remaining positions with zero.

---

### #443 String Compression

Approach:

* Count consecutive characters.
* Write character.
* Write count only if count > 1.

---

# 3. Sort + Two Pointer

## Definition

Sort the array first and then apply two pointers.

Sorting helps pointer movement become predictable.

---

## When To Use

* Pair Sum.
* Triplet Sum.
* Quadruplet Sum.
* Combination searching.
* Duplicate handling.

---

## Template

```java
Arrays.sort(nums);

for(int i = 0; i < n; i++){

    int left = i + 1;
    int right = n - 1;

    while(left < right){

    }
}
```

---

## Problems

### #15 3Sum

Approach:

* Sort array.
* Fix one number.
* Use left and right to find remaining two numbers.
* Skip duplicates.

---

### #16 3Sum Closest

Approach:

* Sort array.
* Fix one number.
* Track closest sum.
* Move pointers based on target comparison.

---

### #18 4Sum

Approach:

* Sort array.
* Fix first number.
* Fix second number.
* Use two pointers for remaining two numbers.
* Skip duplicates.

---

# 4. Sort + Two Pointer + Counting

## Definition

Instead of finding one answer, count multiple valid combinations at once.

---

## When To Use

* Counting triplets.
* Counting valid pairs.
* Counting combinations.
* Triangle problems.

---

## Template

```java
Arrays.sort(nums);

while(left < right){

    if(condition){
        count += (right - left);
        right--;
    }
    else{
        left++;
    }
}
```

---

## Problems

### #611 Valid Triangle Number

Approach:

* Sort array.
* Fix largest side.
* Check triangle condition.
* Count multiple triangles using right - left.

---

### #259 3Sum Smaller

Approach:

* Sort array.
* Fix one number.
* Count all sums smaller than target.
* Use right - left counting optimization.

---

# 5. Sort + Two Pointer + Greedy

## Definition

Combine sorting, greedy decision making and pointer movement.

---

## When To Use

* Capacity problems.
* Weight problems.
* Resource allocation.
* Pairing problems.

---

## Template

```java
Arrays.sort(nums);

while(left <= right){

}
```

---

## Problems

### #881 Boats To Save People

Approach:

* Sort weights.
* Try pairing smallest with largest.
* If possible use one boat.
* Otherwise send largest alone.

---

# 6. Dutch National Flag Pattern

## Definition

Three pointer partitioning algorithm.

Array is divided into three regions.

---

## When To Use

* Three categories.
* 0,1,2 problems.
* Partitioning arrays.

---

## Template

```java
int low = 0;
int mid = 0;
int high = nums.length - 1;
```

---

## Problems

### #75 Sort Colors

Approach:

* 0 goes left.
* 1 stays middle.
* 2 goes right.
* Process entire array in one pass.

---

# 7. Reverse Suffix Pattern

## Definition

Used to generate next lexicographical arrangement.

---

## When To Use

* Permutations.
* Next arrangement problems.
* Lexicographical ordering.

---

## Template

```java
Find Pivot
Find Next Greater Element
Swap
Reverse Suffix
```

---

## Problems

### #31 Next Permutation

Approach:

* Find pivot from right.
* Find next greater value.
* Swap both.
* Reverse remaining suffix.

---

# 8. Floyd Cycle Detection

## Definition

Treat values as pointers and detect cycles.

Also called Tortoise and Hare Algorithm.

---

## When To Use

* Duplicate detection.
* Cycle detection.
* O(1) extra space requirement.

---

## Template

```java
slow = nums[0];
fast = nums[0];

slow = nums[slow];
fast = nums[nums[fast]];
```

---

## Problems

### #287 Find The Duplicate Number

Approach:

* Treat array as linked list.
* Duplicate creates cycle.
* Find meeting point.
* Find cycle entrance.

---

# Final Summary

Two Pointer is one of the most important optimization techniques in Data Structures and Algorithms.

The technique helps reduce unnecessary nested loops by processing elements from multiple directions or using specialized pointer movement strategies.

Most Two Pointer problems belong to one of the following categories:

* Opposite Direction
* Slow Fast Pointer
* Sort + Two Pointer
* Sort + Two Pointer + Counting
* Sort + Two Pointer + Greedy
* Dutch National Flag
* Reverse Suffix
* Floyd Cycle Detection

Mastering these patterns makes many brute force solutions significantly faster and easier to implement.

---

# Golden Rules

### Rule 1

If the array is sorted, always think about Two Pointer before using nested loops.

---

### Rule 2

If sum is too small, move towards larger values.

---

### Rule 3

If sum is too large, move towards smaller values.

---

### Rule 4

Use Slow Fast Pointer when building an answer in-place.

---

### Rule 5

Sort first whenever pointer movement depends on element ordering.

---

### Rule 6

If one valid pair implies multiple valid answers, think about counting optimizations such as:

```java
count += (right - left);
```

---

### Rule 7

Always skip duplicates after sorting when unique answers are required.

---

### Rule 8

When a problem asks for O(1) extra space, consider in-place pointer movement.

---

### Rule 9

Before using O(n²) or O(n³), check whether Two Pointer can reduce complexity.

---

### Rule 10

Ask these questions before solving:

* Is the array sorted?
* Can two pointers replace nested loops?
* Can sorting simplify the solution?
* Can I process elements from both ends?
* Can I build the answer in-place?

If any answer is YES, a Two Pointer solution may exist.
