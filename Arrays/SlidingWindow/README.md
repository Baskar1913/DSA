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
