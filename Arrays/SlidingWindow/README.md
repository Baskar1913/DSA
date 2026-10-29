# Sliding Window Pattern

## Definition

Sliding Window is an array/string optimization technique that processes a **contiguous range of elements** (a window) instead of recalculating every subarray from scratch.

A window is usually represented by two pointers:

- `left` — the index where the current window starts.
- `right` — the index where the current window ends.

The window includes both endpoints, so its length is:

```java
right - left + 1
```

The `right` pointer normally moves forward to include a new element. When the current window violates a condition, the `left` pointer moves forward to remove elements until the window becomes valid again.

Sliding Window is especially useful for problems asking about a **subarray** or **substring**, because these are contiguous ranges.

---

## Why Use Sliding Window?

Consider finding the maximum sum of any 3 consecutive elements in:

```java
int[] nums = {2, 1, 5, 1, 3, 2};
int k = 3;
```

A brute-force solution calculates every 3-element sum from scratch. A sliding-window solution calculates the first sum once, then updates it by removing the element that leaves and adding the element that enters.

```java
windowSum = windowSum - nums[i - k] + nums[i];
```

This avoids repeated work.

- Brute force for all fixed-size windows: commonly `O(n * k)`.
- Sliding Window for a fixed-size window: `O(n)`.
- Extra space: often `O(1)` when only a few variables are needed.

The exact complexity depends on the problem and whether a map, set, or sorting is required.

---

## Important Terms

### 1. Window

A contiguous section of the array between `left` and `right`, including both endpoints.

Example:

```text
nums = [2, 1, 5, 1, 3, 2]
        L        R
window = [2, 1, 5]
```

If `left = 0` and `right = 2`, the window length is:

```java
right - left + 1  // 2 - 0 + 1 = 3
```

### 2. Expand the window

Move `right` forward to include a new element. In the common variable-size pattern, this happens in a `for` loop:

```java
for (int right = 0; right < nums.length; right++) {
    // Add nums[right] to the current window.
}
```

Each iteration expands the window to include the new rightmost element.

### 3. Shrink the window

Move `left` forward to remove one or more elements from the beginning of the current window.

```java
while (conditionIsInvalid) {
    // Remove nums[left] from the window's state.
    left++;
}
```

**Important:** Before increasing `left`, update the running sum, product, frequency map, or other state to remove the element at `nums[left]`.

### 4. Valid window

A window that satisfies the rule in the problem.

Examples:

- Sum is at least `target`.
- There are at most `k` zeroes.
- No value appears more than `k` times.
- The window contains at most two distinct values.

### 5. Answer

Depending on the question, track one of these:

- `maxLen` — longest valid window.
- `minLen` — shortest window that meets a target.
- `maxSum` — maximum sum for a window of a specified size.
- `count` — number of valid subarrays.

---

# The Most Important Part: Understanding `while`, `right`, and `left`

This is the central idea of variable-size Sliding Window.

## General template

```java
int left = 0;
int sum = 0;
int answer = 0;

for (int right = 0; right < nums.length; right++) {

    // 1. Expand: include the new right element.
    sum += nums[right];

    // 2. Shrink: remove elements while the window is invalid.
    while (/* window violates the required condition */) {
        sum -= nums[left];
        left++;
    }

    // 3. Record the answer for the now-valid window.
    answer = Math.max(answer, right - left + 1);
}
```

The condition and the answer update must match the problem. Do not copy this template blindly.

## What does `right` do?

`right` moves from index `0` to `nums.length - 1`. It brings one new element into the window during each iteration.

For example, when `right = 3`, the current window ends at index `3`.

## What does `left` do?

`left` marks the first element still included in the window. It moves forward only when the algorithm needs to remove elements from the beginning.

When `left` moves from `0` to `1`, `nums[0]` has left the window. When it moves from `1` to `2`, `nums[1]` has left too.

`left` usually never moves backwards.

## Why is it `while`, not `if`?

Use `while` when **one removal might not be enough** to restore the condition.

Suppose the rule is that the window sum must be at most `7`:

```text
nums = [2, 3, 4]
```

When `right = 2`, adding `4` makes the sum `9`. Remove `2`, and the sum becomes `7`, so the window is valid. In this example one removal is enough.

But consider:

```text
nums = [1, 2, 8]
```

When `8` enters, the sum becomes `11`. Removing `1` leaves `10`, which is still too large. Removing `2` leaves `8`, which is still too large. The algorithm must keep shrinking until the condition is satisfied (or the window is empty).

A single `if` would remove only one element and might leave an invalid window. A `while` repeats the removal as many times as necessary.

## What happens inside the `while` loop?

Use this order:

```java
while (sum > target) {
    sum -= nums[left]; // Remove the outgoing element's contribution.
    left++;            // Move the window start forward.
}
```

1. Check whether the current window violates the rule.
2. Remove the element at the current `left` from the running state.
3. Advance `left`.
4. Check the condition again.
5. Stop only when the condition is no longer true.

**Never increase `left` without updating the window state first**, or your sum/count/map may no longer represent the actual window.

## When should the answer be updated?

This depends on the problem.

- **Longest valid window:** shrink until valid, then update `maxLen`.
- **Shortest window whose sum is at least a target:** while the condition is satisfied, record the length and shrink to try to make it shorter.
- **Count valid subarrays ending at `right`:** after restoring validity, add `right - left + 1` when the problem's conditions justify that counting rule.
- **Fixed-size window:** add the incoming element and remove the outgoing element; update the answer once the window reaches size `k`.

---

# Step-by-Step Trace: Variable-Size Window

Find the longest subarray with sum at most `7` in a positive-number array.

```java
int[] nums = {1, 2, 8, 1};
int left = 0;
int sum = 0;
int maxLen = 0;

for (int right = 0; right < nums.length; right++) {
    sum += nums[right];

    while (sum > 7) {
        sum -= nums[left];
        left++;
    }

    maxLen = Math.max(maxLen, right - left + 1);
}
```

| `right` | Added | Sum before shrinking | Shrink action | Valid window after shrinking | Length |
|---:|---:|---:|---|---|---:|
| 0 | 1 | 1 | None | `[1]` | 1 |
| 1 | 2 | 3 | None | `[1, 2]` | 2 |
| 2 | 8 | 11 | Remove 1, then 2, then 8 | `[]` | 0 |
| 3 | 1 | 1 | None | `[1]` | 1 |

The longest valid window has length `2`, from `[1, 2]`.

Notice that when `8` enters, `while (sum > 7)` executes repeatedly. Since `8` alone is too large, `left` moves beyond `right` after removing all elements in that window. This is okay for this pattern; the next iteration starts a new window at the next index.

**Sliding Window limitation:** sum-based shrinking like this generally relies on non-negative/positive values so that extending a window cannot decrease its sum. With arbitrary negative numbers, this logic may not work.

---

# General Templates

## Template A: Fixed-Size Sliding Window

Use when the window size must always be exactly `k`.

```java
int windowSum = 0;

// Build the first window of size k.
for (int i = 0; i < k; i++) {
    windowSum += nums[i];
}

int maxSum = windowSum;

// Slide one position at a time.
for (int right = k; right < nums.length; right++) {
    windowSum -= nums[right - k]; // Element leaving on the left.
    windowSum += nums[right];     // Element entering on the right.

    maxSum = Math.max(maxSum, windowSum);
}
```

The window size stays `k`. When `right` enters, index `right - k` leaves.

## Template B: Variable-Size Sliding Window — Longest Valid Window

Use when you want the longest window that obeys a rule.

```java
int left = 0;
int maxLen = 0;

for (int right = 0; right < nums.length; right++) {
    // Add nums[right] to the window's state.

    while (/* window is invalid */) {
        // Remove nums[left] from the state.
        left++;
    }

    maxLen = Math.max(maxLen, right - left + 1);
}
```

## Template C: Variable-Size Sliding Window — Shortest Window Meeting a Target

Use when a window becomes a candidate answer once a condition is true.

```java
int left = 0;
int sum = 0;
int minLen = Integer.MAX_VALUE;

for (int right = 0; right < nums.length; right++) {
    sum += nums[right];

    while (sum >= target) {
        minLen = Math.min(minLen, right - left + 1);
        sum -= nums[left];
        left++;
    }
}

return minLen == Integer.MAX_VALUE ? 0 : minLen;
```

For this sum-based approach, the standard guarantee is positive/non-negative input appropriate to the problem; arbitrary negative values can invalidate the shrinking logic.

## Template D: Count Valid Subarrays Ending at `right`

For problems where every subarray ending at `right` and starting at any index from `left` through `right` is valid after shrinking:

```java
int left = 0;
long count = 0;

for (int right = 0; right < nums.length; right++) {
    // Add nums[right] to the window state.

    while (/* window violates the rule */) {
        // Remove nums[left] from the state.
        left++;
    }

    count += right - left + 1;
}
```

Why `right - left + 1`? The valid subarrays ending at `right` are:

```text
[left ... right]
[left+1 ... right]
[left+2 ... right]
...
[right ... right]
```

There are exactly `right - left + 1` such subarrays. This counting trick is only correct when the problem's validity rule guarantees all those suffixes are valid.

---

# 1. Fixed-Size Sliding Window

## Definition

The window always contains exactly `k` consecutive elements.

## When To Use

- Maximum or minimum sum of `k` consecutive elements.
- Maximum average of `k` elements.
- Count windows meeting an average threshold.
- Moving averages.
- Fixed-size neighborhoods around each index.

## Key Rule

Once the first window is calculated, each step removes one outgoing element and adds one incoming element:

```java
windowSum = windowSum - nums[right - k] + nums[right];
```

## Problems in This Folder

### `MaxSumSubArray.java`

Find the maximum sum of `k` consecutive elements.

- Build the first window.
- Slide it by removing the old leftmost element and adding the new element.
- Keep the maximum sum.

### `MaxAvgSubArray.java`

Find the maximum average of any `k` consecutive elements.

- Use the same running-sum technique.
- Compare `sum / k` for each window.
- Since every window has the same size, comparing sums also identifies the maximum average.

### `NoofSubArrays.java`

Count fixed-size windows whose average is at least a threshold.

- Convert the average condition to `sum >= k * threshold`.
- Build the first window.
- Slide the window and check each sum.

### `MaxNoCustomer.java`

Maximize satisfied customers by choosing a fixed `minutes` interval to suppress grumpiness.

- Count customers already satisfied when `grumpy[i] == 0`.
- Use a fixed window to calculate additional customers who could be satisfied.
- Slide the window and retain the best additional total.

### `SubArrayAverage.java`

Return the average of a radius-`k` window centered at each index.

- Window size is `2 * k + 1`.
- Indices without a complete centered window remain `-1`.
- Use a `long` running sum to reduce overflow risk.

### `HighScore.java`

Choose exactly `k` cards from either end to maximize the score.

- Initially take the first `k` cards.
- Repeatedly replace one selected card from the left group with one card from the right end.
- Track the maximum score.
- This is a fixed-count, two-end variation of sliding window.

---

# 2. Variable-Size Sliding Window

## Definition

The window grows and shrinks depending on a condition. Its size is not fixed.

- `right` expands the window.
- `while` shrinks the window if the condition is violated.
- `left` moves forward as elements are removed.

## When To Use

- Longest subarray satisfying a constraint.
- Shortest subarray reaching a target.
- At most `k` distinct values.
- At most `k` zeroes.
- Sum/product constraints under appropriate input assumptions.
- Problems where the window must be repaired after adding an element.

## Problems in This Folder

### `MinSubArrayLength.java` and `SmallestSubArray.java`

Find the shortest subarray whose sum is at least a target.

- Add the right element to `sum`.
- While `sum >= target`, record the current length and remove the left element.
- Continue shrinking because a shorter valid window may exist.
- Return `0` if no valid window exists.

### `MinOperations.java`

Find the minimum number of operations to reduce `x` to zero by removing elements from the ends.

- Calculate `target = totalSum - x`.
- Instead of selecting removed elements, find the longest contiguous subarray with sum `target`.
- Return `nums.length - maxLen`.
- The standard sliding-window implementation assumes non-negative values.

### `LongSubArrayDelElement.java`

Find the longest subarray of `1`s after deleting exactly one element.

- Count zeroes in the window.
- Keep at most one zero with `while (zeros > 1)`.
- Track `right - left`, not `right - left + 1`, because one element must be deleted.

### `MaxConsOnes.java`

Find the longest subarray containing at most `k` zeroes.

- Increment `zeros` when a zero enters.
- While `zeros > k`, remove elements from the left.
- Update the longest valid length.

### `LenLongSubArray.java`

Find the longest subarray in which each value appears at most `k` times.

- Store frequencies in a `HashMap`.
- Add the incoming value's frequency.
- While the incoming value's frequency exceeds `k`, shrink from the left and update frequencies.
- Track the maximum window length.

### `MaxFruits.java`

Find the longest subarray containing at most two distinct fruit types.

- Track each fruit's frequency in a `HashMap`.
- While `map.size() > 2`, remove elements from the left.
- Remove a key when its frequency reaches zero.
- Track the longest valid window.

### `NoSubArrProduct.java`

Count contiguous subarrays whose product is less than `k`.

- If `k <= 1`, return `0` for the usual positive-integer input constraints.
- Multiply by each incoming value.
- While `product >= k`, divide out the leftmost value and advance `left`.
- Add `right - left + 1` to the count.

This implementation assumes positive array elements; zero or negative values need different handling.

### `CountSubArray.java`

Count subarrays whose `sum * length` is less than `k`.

- Add the new value to the running sum.
- While `sum * (right - left + 1) >= k`, shrink from the left.
- Add `right - left + 1` valid subarrays ending at `right`.

This monotonic-window method assumes positive values as required by the usual problem constraints.

### `CountSubArrays.java`

Count subarrays in which the global maximum value appears at least `k` times.

- Find the maximum value in the array.
- Count its occurrences in the current window.
- When the window contains at least `k` occurrences, count `nums.length - right` valid extensions.
- Remove the leftmost value and continue while the condition remains true.

### `ContainDuplicate.java`

Check whether equal values occur within distance `k`.

- Store the latest index for each value in a `HashMap`.
- If the value has been seen and `i - previousIndex <= k`, return `true`.
- Update the latest index.
- This is a related sliding-window/index-distance problem implemented with a map rather than explicit `left` and `right` pointers.

---

# 3. Sliding Window + HashMap / Frequency Counting

## Definition

Use a frequency map to track how many times each value occurs inside the current window.

```java
map.put(value, map.getOrDefault(value, 0) + 1);
```

When removing a value:

```java
map.put(value, map.get(value) - 1);

if (map.get(value) == 0) {
    map.remove(value);
}
```

Removing keys at frequency zero is important because `map.size()` is often used to count the number of distinct values.

## When To Use

- At most `k` distinct values.
- Exactly `k` distinct values (often by combining two at-most counts).
- Frequency restrictions.
- Fixed-size windows with distinct elements.
- Counting values that repeat within a range.

## Problems in This Folder

### `MaxFruits.java`

At most two distinct values: shrink while `map.size() > 2`.

### `LenLongSubArray.java`

At most `k` occurrences of each value: shrink while the newly added value's frequency exceeds `k`.

### `DistinctSubArray.java`

Find the maximum sum of a fixed-size `k` window containing `k` distinct values.

- Maintain both the window sum and value frequencies.
- If the window grows beyond `k`, remove its leftmost value.
- When window size is `k` and `map.size() == k`, evaluate the sum.

### `UniqueSubArray.java`

Find the maximum sum of a fixed-size window of length `k` with at least `m` distinct values.

- Maintain a running sum and frequency map.
- Keep the window size at most `k`.
- When its size is `k` and the number of distinct values is at least `m`, evaluate the sum.

---

# 4. Sliding Window + Sorting

## Definition

Sort the values first, then use a window to find the largest group or smallest range satisfying a numeric difference constraint.

Sorting changes the original order, so use this only when the problem allows reordering and does not require preserving the original sequence.

## Problems in This Folder

### `MinDifference.java`

Find the minimum difference between the maximum and minimum among any `k` selected values.

- Sort the array.
- Every group of `k` consecutive sorted values is a candidate.
- Compute `nums[i + k - 1] - nums[i]`.
- Return the smallest difference.

This is often described as a sorted fixed-size window.

### `MaxBeauty.java`

Find the largest group of values that can be adjusted within the allowed limit to reach the same value.

- Sort the array.
- Maintain a window where `nums[right] - nums[left] <= 2 * k`.
- While the difference is too large, advance `left`.
- Track the maximum window size.

### `MinNumOperations.java`

Find the minimum operations needed to make the array continuous.

- Sort the array.
- Build a list of unique values.
- Maintain a window where `unique[right] - unique[left] < n`.
- The largest such window is the maximum number of values that can remain.
- Return `n - maxCount`.

---

# 5. Sliding Window + Running Sum

## Definition

A running sum stores the sum of the current window. Instead of summing all values again, update it incrementally.

```java
sum += nums[right];  // Incoming value

while (condition) {
    sum -= nums[left]; // Outgoing value
    left++;
}
```

For fixed-size windows:

```java
sum += nums[right] - nums[right - k];
```

## Benefits

- Avoid repeated summation.
- Usually reduce a quadratic approach to linear time.
- Works well for maximum/minimum sum, average, and sum constraints.

## Important Warning

The usual sum-based variable-window technique relies on input constraints that make the sum move predictably as the window expands or shrinks. With negative numbers, removing an element can increase the sum, and adding an element can decrease it. In those cases, the standard `while (sum > target)` or `while (sum < target)` logic may not be correct.

---

# 6. Sliding Window + Greedy / Transformation

Some problems become easier when the desired result is transformed into a contiguous-window problem.

### `MinOperations.java`

Instead of choosing elements to remove from the left and right to total `x`, use:

```java
target = totalSum - x;
```

Then find the longest subarray whose sum is `target`. The answer is:

```java
nums.length - maxLen;
```

### `HighScore.java`

Choosing `k` cards from either end can be viewed as choosing some cards from the left and the rest from the right. Start with all `k` from the left, then shift the split one card at a time.

### `MaxNoCustomer.java`

Separate customers already satisfied from the extra customers gained by applying the technique for a fixed interval. A fixed-size window identifies the interval with the greatest extra gain.

---

# Choosing the Correct Pattern

| Clue in the problem | Pattern to consider | Typical state |
|---|---|---|
| Exactly `k` consecutive elements | Fixed-size window | Sum/count |
| Longest window satisfying a rule | Variable-size window | `maxLen` |
| Shortest window reaching a target | Variable-size window | `minLen` |
| At most `k` zeroes | Variable-size window | `zeros` |
| At most two distinct values | Window + frequency map | `map.size()` |
| Each value appears at most `k` times | Window + frequency map | Frequencies |
| Count valid subarrays ending at `right` | Variable window + counting | `right - left + 1` |
| Product less than `k` | Variable window | Running product |
| Maximum sum with all values distinct | Fixed-size + frequency map | `sum`, `map.size()` |
| Minimize range among `k` selected values | Sort + fixed-size window | `max - min` |
| Values can be adjusted within a range | Sort + variable-size window | Difference constraint |

---

# Common Mistakes

## Mistake 1: Forgetting `+1` in the window length

Incorrect:

```java
int length = right - left;
```

Correct for a normal inclusive window:

```java
int length = right - left + 1;
```

Exception: in `LongSubArrayDelElement`, `right - left` is intentional because exactly one element must be deleted.

## Mistake 2: Using `if` instead of `while`

Incorrect for a condition that may require multiple removals:

```java
if (sum > target) {
    sum -= nums[left];
    left++;
}
```

Usually correct for that variable-window pattern:

```java
while (sum > target) {
    sum -= nums[left];
    left++;
}
```

## Mistake 3: Moving `left` without updating state

Incorrect:

```java
left++;
```

Correct when tracking a sum:

```java
sum -= nums[left];
left++;
```

For a frequency map, decrement the outgoing value's count and remove its key if the count becomes zero.

## Mistake 4: Updating the answer at the wrong time

- For a longest valid window, normally update after shrinking to a valid state.
- For a shortest window that meets a target, update while the window still satisfies the target, before removing the leftmost element.
- For a fixed-size window, update only after the window has the required size.

## Mistake 5: Assuming all arrays work with sum-based windows

The standard shrinking logic for positive numbers may fail when negative values are allowed. Check the problem constraints before choosing the pattern.

## Mistake 6: Forgetting to remove zero-frequency map keys

If a key stays in the map after its count becomes zero, `map.size()` will incorrectly count that value as present in the window.

## Mistake 7: Integer overflow

Use `long` when sums, products, or the number of subarrays can exceed the `int` range.

---

# Time and Space Complexity

Let `n` be the array length.

- A standard fixed-size sliding window: `O(n)` time and usually `O(1)` extra space.
- A standard variable-size window: usually `O(n)` time because each pointer moves forward at most `n` times.
- A window with a `HashMap`: usually `O(n)` time and up to `O(n)` extra space.
- Sorting followed by a window: typically `O(n log n)` time for sorting plus `O(n)` for the scan.
- Counting subarrays can produce up to `n * (n + 1) / 2` answers, so use `long` if needed.

The nested `while` loop does not automatically make a sliding-window algorithm `O(n²)`. Although the `while` may execute many times in one iteration, `left` only moves forward across the array once in total. The total number of left-pointer increments is at most `n`.

---

# Golden Rules

### Rule 1

If the problem asks about a contiguous subarray or substring, consider Sliding Window.

### Rule 2

For a fixed-size window, build the first window once, then remove the outgoing value and add the incoming value.

### Rule 3

In a variable-size window, `right` expands the window and `left` shrinks it.

### Rule 4

Use `while` when the window may need several removals before it becomes valid.

### Rule 5

Update the running state before incrementing `left`.

### Rule 6

For the longest valid window, shrink until valid, then update the maximum length.

### Rule 7

For the shortest window meeting a condition, record the candidate while the condition is true, then shrink to try to improve it.

### Rule 8

For counting valid subarrays, `right - left + 1` counts valid suffixes ending at `right` only when the problem's condition guarantees those suffixes are all valid.

### Rule 9

For frequency maps, remove values whose count reaches zero.

### Rule 10

Check the input constraints. Standard sum/product windows often rely on positive or non-negative values.

---

# Programs in This Folder

The `SlidingWindow` ZIP provided with this README contains these Java programs:

- `ContainDuplicate.java`
- `CountSubArray.java`
- `CountSubArrays.java`
- `DistinctSubArray.java`
- `HighScore.java`
- `LenLongSubArray.java`
- `LongSubArrayDelElement.java`
- `MaxAvgSubArray.java`
- `MaxBeauty.java`
- `MaxConsOnes.java`
- `MaxFruits.java`
- `MaxNoCustomer.java`
- `MaxSumSubArray.java`
- `MinDifference.java`
- `MinNumOperations.java`
- `MinOperations.java`
- `MinSubArrayLength.java`
- `MostFreqElement.java`
- `NoofSubArrays.java`
- `NoSubArrProduct.java`
- `SmallestSubArray.java`
- `SubArrayAverage.java`
- `UniqueSubArray.java`

**Package note:** The Java files in the supplied ZIP currently declare `package Arrays.SlidingWindow;`. If your actual Eclipse package is named `Arrays.slidingwindow` (lowercase `s`), make the package declarations match that exact case. Java package names are case-sensitive.

---

# Final Summary

Sliding Window avoids recalculating overlapping contiguous ranges from scratch. The two key operations are expanding with `right` and, when necessary, shrinking with `left`.

The most important skill is understanding **when the window becomes invalid, what must be removed from its state, and when the answer should be updated**.

Before coding, ask:

1. Is the target window fixed-size or variable-size?
2. What state must I maintain: sum, product, zero count, or frequencies?
3. What makes the current window invalid?
4. Should I shrink with `while`?
5. Do I want the longest window, shortest window, maximum/minimum value, or number of subarrays?
6. Do the input constraints support the standard sliding-window logic?

Master these decisions, and the majority of Sliding Window problems become easier to recognize and implement.
