# NeetCode Solutions & Notes — @Rishi-Srivastav

> Synced automatically from [NeetCode.io](https://neetcode.io) · Repository: `neetcode-submissions`

---

# DSA Pattern Notes — neetcode-submissions

Source: `github.com/Rishi-Srivastav/neetcode-submissions` (36 solved problems, all Java)

Goal of this doc: don't memorize 36 problems — memorize **9 patterns**. Every problem in the repo is one of these in disguise. When a new interview problem shows up, the job is to recognize which bucket it's in, then the template is already in your head.

---

## 1. Hashing for Lookup / Complement Search

**Signal:** "find a pair/duplicate," "have I seen this before," O(n) required where brute force is O(n²).

**Template:**
```java
HashMap<Integer, Integer> map = new HashMap<>();
for (int i = 0; i < n; i++) {
    if (map.containsKey(target - nums[i])) { /* found pair */ }
    map.put(nums[i], i);
}
```
**Key idea:** check-before-insert (or insert-then-check depending on duplicate rules) turns nested loops into a single pass.

**Repo problems:** `two-integer-sum`, `duplicate-integer`, `is-anagram` (frequency map instead of complement)

---

## 2. Stack for "Most Recent Unmatched Thing"

**Signal:** Matching pairs, nesting, "closest previous element that satisfies X."

**Template:**
```java
Deque<Character> stack = new ArrayDeque<>();
for (char c : s.toCharArray()) {
    if (isOpening(c)) stack.push(c);
    else {
        if (stack.isEmpty() || !matches(stack.pop(), c)) return false;
    }
}
return stack.isEmpty();
```
**Key idea:** stack is the right structure whenever the answer depends on the *most recent unclosed* item — brackets, monotonic stacks, backtracking undo.

**Repo problems:** `validate-parentheses`

---

## 3. Binary Search on Sorted / Rotated Space

**Signal:** Sorted array (or "sorted then rotated"), O(log n) required.

**Template (rotated array minimum):**
```java
int lo = 0, hi = nums.length - 1;
while (lo < hi) {
    int mid = lo + (hi - lo) / 2;
    if (nums[mid] > nums[hi]) lo = mid + 1;   // min is right of mid
    else hi = mid;                             // min is at mid or left
}
return nums[lo];
```
**Key idea:** at every step, compare `mid` against an endpoint to figure out which half is "normally sorted," then discard the half that can't contain the answer. Don't search for the target directly — search for the *property* (rotation point, first true, etc.).

**Repo problems:** `find-minimum-in-rotated-sorted-array`

---

## 4. Linked List Pointer Manipulation

**Signal:** Reverse, merge, detect cycle, find middle — anything "in-place, O(1) space" on a linked list.

**Template (reverse):**
```java
ListNode prev = null, curr = head;
while (curr != null) {
    ListNode next = curr.next;
    curr.next = prev;
    prev = curr;
    curr = next;
}
return prev;
```
**Template (merge two sorted):**
```java
ListNode dummy = new ListNode(-1), tail = dummy;
while (l1 != null && l2 != null) {
    if (l1.val <= l2.val) { tail.next = l1; l1 = l1.next; }
    else { tail.next = l2; l2 = l2.next; }
    tail = tail.next;
}
tail.next = (l1 != null) ? l1 : l2;
return dummy.next;
```
**Key idea:** always use a `dummy` head to avoid special-casing the first node, and always save `next` before you rewire `.next`.

**Repo problems:** `reverse-a-linked-list`, `merge-two-sorted-linked-lists`

---

## 5. Tree Recursion (DFS on Trees)

**Signal:** Anything on a binary tree — depth, comparison, subtree, ancestor. 90% of tree problems are "recurse on left, recurse on right, combine."

**Template (generic recursive shape):**
```java
int solve(TreeNode node) {
    if (node == null) return baseCase;
    int left = solve(node.left);
    int right = solve(node.right);
    return combine(node.val, left, right);
}
```
**BST-specific trick (lowest common ancestor):** use the BST ordering property instead of full traversal:
```java
TreeNode lca(TreeNode node, TreeNode p, TreeNode q) {
    if (p.val < node.val && q.val < node.val) return lca(node.left, p, q);
    if (p.val > node.val && q.val > node.val) return lca(node.right, p, q);
    return node; // split point
}
```
**Key idea:** define what the recursive call *returns* (a boolean? a depth? a node?) before writing the base case — that's 80% of the design work.

**Repo problems:** `depth-of-binary-tree`, `invert-a-binary-tree`, `same-binary-tree`, `subtree-of-a-binary-tree`, `lowest-common-ancestor-in-binary-search-tree`

---

## 6. Heap / Priority Queue for "Top-K" or "Running Median"

**Signal:** "k largest/smallest," "running median," "kth something in a stream."

Trade-offs: Max-Heap vs. Min-HeapWhile a Max-Heap works well, a Min-Heap approach is often preferred if k is much smaller than n. A Min-Heap approach is better because it uses significantly less memory and runs faster when the array size (\(n\)) is large, and you only need a few top elements (\(k\)).Instead of putting all \(n\) elements into a heap, you maintain a heap containing only \(k\) elements.

** Summary Rule of Thumb ** :To find k Largest -> Use a Min-Heap of size k.To find k Smallest -> Use a Max-Heap of size k.
**Template (top-k via min-heap of size k):**
```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
for (int num : nums) {
    minHeap.offer(num);
    if (minHeap.size() > k) minHeap.poll();
}
```
**Template (running median via two heaps):**
```java
PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder()); // max-heap
PriorityQueue<Integer> large = new PriorityQueue<>();                            // min-heap
// keep small.size() >= large.size(), rebalance after every insert
// median = small.peek() (odd) or avg of both tops (even)
```
**Key idea:** heap size is your budget — bound it to `k` and you get O(n log k) instead of O(n log n) full sort.

**Repo problems:** `top-k-elements-in-list`, `find-median-in-a-data-stream`

---

## 7. Backtracking (DFS + Undo)

**Signal:** "all combinations/permutations/paths," "find a path in a grid," constraint satisfaction.

**Template:**
```java
void backtrack(List<Integer> path, int start, int remaining) {
    if (remaining == 0) { res.add(new ArrayList<>(path)); return; }
    for (int i = start; i < candidates.length; i++) {
        if (candidates[i] > remaining) continue; // prune
        path.add(candidates[i]);
        backtrack(path, i, remaining - candidates[i]);   // i, not i+1 → reuse allowed
        path.remove(path.size() - 1);                     // undo = the whole point
    }
}
```
**Grid word search variant:** DFS with a visited marker that gets *unmarked* on the way back up — same undo principle, applied to a 2D grid instead of a candidate list.

**Key idea:** backtracking = DFS + "try it, recurse, then undo the choice." The undo step is what people forget.

**Repo problems:** `combination-target-sum`, `search-for-word`

---

## 8. Graph Traversal (DFS/BFS, Topological Sort, Union-Find)

**Signal:** Grid of cells, adjacency list, "is it connected," "can you finish all tasks," "count regions."

**Template (flood fill / island counting):**
```java
void dfs(int[][] grid, int i, int j) {
    if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return;
    if (grid[i][j] != 1) return;
    grid[i][j] = 0; // mark visited by mutating (or use a visited[][] set)
    dfs(grid, i+1, j); dfs(grid, i-1, j); dfs(grid, i, j+1); dfs(grid, i, j-1);
}
```
**Multi-source DFS (Pacific Atlantic pattern):** instead of asking "can every cell reach the ocean," flip it — start DFS *from every ocean-adjacent cell* and flow inland. Any problem with "reachable from multiple sources" flips this way.

**Template (topological sort / cycle detection — course schedule):**
```java
// 3-state DFS: 0 = unvisited, 1 = in current path, 2 = fully done
boolean hasCycle(int node, int[] state, List<List<Integer>> adj) {
    if (state[node] == 1) return true;   // back edge → cycle
    if (state[node] == 2) return false;
    state[node] = 1;
    for (int next : adj.get(node)) if (hasCycle(next, state, adj)) return true;
    state[node] = 2;
    return false;
}
```
**Key idea:** grid problems are graphs where adjacency = 4-directional neighbors. "Can I finish all courses" = "is there a cycle in the dependency graph." Valid tree = `edges == nodes - 1` AND connected (no cycle).

**Repo problems:** `count-number-of-islands`, `clone-graph`, `course-schedule`, `pacific-atlantic-water-flow`, `valid-tree`, `count-paths`


## 5. Tree Recursion (DFS on Trees)

Signal: Anything on a binary tree — depth, comparison, subtree, validation, ancestor. Most tree problems are "recurse on left, recurse on right, combine."

Template (generic recursive shape):

```java
int solve(TreeNode node) {
    if (node == null) return baseCase;

    int left = solve(node.left);
    int right = solve(node.right);

    return combine(node.val, left, right);
}
```

Key idea: Before writing recursion, define exactly what the recursive call returns — boolean, depth, node, etc. Then define the base case and combine the left/right results.

### Tree comparison

For problems like `same-binary-tree`, compare the two trees at the same time:

```java
boolean same(TreeNode p, TreeNode q) {
    if (p == null && q == null) return true;
    if (p == null || q == null) return false;
    if (p.val != q.val) return false;

    return same(p.left, q.left) &&
           same(p.right, q.right);
}
```

For `subtree-of-a-binary-tree`, use the same comparison as a helper and try every node as a possible root of the subtree.

Key idea: `subtree` is really two problems:

1. Traverse the main tree looking for a candidate root.
2. Compare the candidate subtree with the target tree.

### BST validation — carry constraints down the tree

Signal: "Is this binary tree a valid BST?"

Do **not** only compare a node with its immediate children. A node must satisfy the ordering constraints imposed by **all its ancestors**.

```java
boolean isValidBST(TreeNode root) {
    return bst(root, Long.MIN_VALUE, Long.MAX_VALUE);
}

boolean bst(TreeNode node, long min, long max) {
    if (node == null) return true;

    if (node.val <= min || node.val >= max)
        return false;

    return bst(node.left, min, node.val) &&
           bst(node.right, node.val, max);
}
```

Key idea: every recursive call carries the valid range for that subtree.

* Going left → upper bound becomes `node.val`
* Going right → lower bound becomes `node.val`

This catches cases such as a value being smaller than its parent but still invalid because it violates a grandparent's constraint. The repo solution uses exactly this min/max-bound technique.

### BST-specific LCA

Signal: Lowest common ancestor in a **BST**.

Use the BST ordering property instead of traversing the whole tree:

```java
TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
    if (p.val < root.val && q.val < root.val)
        return lca(root.left, p, q);

    if (p.val > root.val && q.val > root.val)
        return lca(root.right, p, q);

    return root;
}
```

Key idea: if both nodes are on the same side, move there. Otherwise the current node is the split point and therefore the LCA.

This gives `O(h)` time instead of traversing the entire tree. The repo solution follows this exact split-point approach.

Repo problems: `depth-of-binary-tree`, `invert-a-binary-tree`, `same-binary-tree`, `subtree-of-a-binary-tree`, `valid-binary-search-tree`, `lowest-common-ancestor-in-binary-search-tree`

---

## 8. Graph Traversal (DFS/BFS, Connected Components, Cycle Detection)

Signal: Adjacency list, grid of cells, "is it connected," "count regions," "can I reach," dependencies/prerequisites, or cloning a graph.

### Graph representation

For an edge list:

```java
List<List<Integer>> graph = new ArrayList<>();

for (int i = 0; i < n; i++)
    graph.add(new ArrayList<>());

for (int[] edge : edges) {
    graph.get(edge[0]).add(edge[1]);
    graph.get(edge[1]).add(edge[0]); // undirected
}
```

Key idea: convert the edge list into an adjacency list first. After that, most graph problems become DFS/BFS over neighbors.

### Connected components

Signal: "How many disconnected groups/components are there?"

Pattern:

```java
boolean[] visited = new boolean[n];
int count = 0;

for (int i = 0; i < n; i++) {
    if (!visited[i]) {
        dfs(i);
        count++;
    }
}
```

Key idea: every time you encounter an unvisited node, you have discovered a **new component**. DFS/BFS marks the entire component, so the outer loop only increments once per component.

Your `count-connected-components` solution uses exactly this pattern.

### Grid = graph

Signal: Grid with islands, regions, connected cells, or neighboring cells.

Treat every cell as a graph node and the four directions as edges:

```java
int[][] dirs = {
    {1, 0}, {-1, 0},
    {0, 1}, {0, -1}
};
```

Typical DFS:

```java
int dfs(int[][] grid, int r, int c) {
    if (r < 0 || r >= grid.length ||
        c < 0 || c >= grid[0].length ||
        grid[r][c] == 0) {
        return 0;
    }

    grid[r][c] = 0; // mark visited

    return 1
        + dfs(grid, r + 1, c)
        + dfs(grid, r - 1, c)
        + dfs(grid, r, c + 1)
        + dfs(grid, r, c - 1);
}
```

Key idea: if the grid itself can be modified, using `grid[r][c] = 0` as the visited marker avoids an additional `visited[][]` array.

For `count-number-of-islands`, count each new unvisited land cell as a new island. For `max-area-of-island`, return the size of the DFS instead. Your `max-area-of-island` solution uses this exact "return area from DFS" pattern.

### Clone graph — DFS + HashMap

Signal: "Create a deep copy/clone of a graph."

The important problem is that graphs can contain **cycles**, so blindly recursively cloning neighbors can loop forever.

Pattern:

```java
Map<Node, Node> map = new HashMap<>();

Node cloneGraph(Node node) {
    if (node == null) return null;

    if (map.containsKey(node))
        return map.get(node);

    Node clone = new Node(node.val);

    // Store BEFORE visiting neighbors.
    map.put(node, clone);

    for (Node neighbor : node.neighbors) {
        clone.neighbors.add(cloneGraph(neighbor));
    }

    return clone;
}
```

Key idea: **create + store the clone before recursively processing neighbors**.

That gives two guarantees:

1. Already-seen nodes are not cloned again.
2. Cycles terminate because a previously seen node is returned from the map.

Your `clone-graph` solution uses this exact approach.

### Undirected graph cycle detection

Signal: "Does this undirected graph contain a cycle?" or "Is this graph a valid tree?"

When doing DFS in an undirected graph, seeing the node you came from is normal. Therefore, track the **parent**.

```java
boolean hasCycle(int node, int parent) {
    visited[node] = true;

    for (int neighbor : graph.get(node)) {
        if (neighbor == parent)
            continue;

        if (visited[neighbor])
            return true;

        if (hasCycle(neighbor, node))
            return true;
    }

    return false;
}
```

Key idea:

```text
visited neighbor + neighbor != parent → cycle
```

For `valid-tree`, there are two things to verify:

```text
1. Exactly n - 1 edges
2. Graph is connected / has no cycle
```

Your solution explicitly checks `edges.length == n - 1`, then performs DFS cycle detection while tracking the parent, and finally verifies that every node was visited.

### Directed graph cycle detection — 3 states

Signal: Dependencies / prerequisites / "can I finish all tasks?"

Use three states:

```text
0 = not visited
1 = currently in DFS path
2 = completely processed
```

```java
boolean hasCycle(int node) {
    if (state[node] == 1) return true;  // back edge
    if (state[node] == 2) return false;

    state[node] = 1;

    for (int next : graph.get(node)) {
        if (hasCycle(next))
            return true;
    }

    state[node] = 2;
    return false;
}
```

Key idea: encountering a node in state `1` means we reached a node that is **already in the current recursion path**, which means there is a directed cycle.

For `course-schedule`:

```text
course A requires B
B → A
```

A cycle such as:

```text
A → B → C → A
```

means the prerequisites can never be completed.

Your `course-schedule` implementation uses this exact 3-state DFS.

### Reverse the direction when reachability is awkward

Signal: "Which cells/nodes can reach multiple destinations/sources?"

For `pacific-atlantic-water-flow`, the naive question is:

> From each cell, can water reach both oceans?

Instead, **reverse the search**:

> Starting from each ocean, which cells can reach that ocean?

Water normally flows from high → low. During reverse DFS, move from low → high:

```text
Ocean boundary
      ↑
      ↑ reverse flow
      ↑
  reachable cells
```

Maintain two visited matrices:

```java
boolean[][] pacific;
boolean[][] atlantic;
```

Run DFS from all Pacific-border cells and all Atlantic-border cells, then take the intersection:

```java
if (pacific[i][j] && atlantic[i][j])
    result.add(Arrays.asList(i, j));
```

Key idea: **when the destination is fixed and there are many possible starting points, reverse the search from the destination.**

Your solution starts DFS from both ocean boundaries and intersects the two reachable sets.

Repo problems: `count-number-of-islands`, `max-area-of-island`, `clone-graph`, `count-connected-components`, `course-schedule`, `pacific-atlantic-water-flow`, `valid-tree`

---

## Quick Recall Table

| Pattern              | Trigger phrase to listen for              | Repo anchor problem                            |
| -------------------- | ----------------------------------------- | ---------------------------------------------- |
| Tree DFS             | "depth," "compare trees," "subtree"       | `depth-of-binary-tree`                         |
| Tree mutation        | "invert/modify every node"                | `invert-a-binary-tree`                         |
| Tree comparison      | "are these trees the same?"               | `same-binary-tree`                             |
| Subtree search       | "is one tree contained in another?"       | `subtree-of-a-binary-tree`                     |
| BST bounds           | "is this a valid BST?"                    | `valid-binary-search-tree`                     |
| BST LCA              | "lowest common ancestor in BST"           | `lowest-common-ancestor-in-binary-search-tree` |
| Graph traversal      | "connected," "reachable," "regions"       | `count-connected-components`                   |
| Grid DFS             | "islands," "connected cells," "area"      | `max-area-of-island`                           |
| Graph cloning        | "deep copy graph"                         | `clone-graph`                                  |
| Undirected cycle     | "valid tree," "cycle in undirected graph" | `valid-tree`                                   |
| Directed cycle       | "prerequisites," "dependencies"           | `course-schedule`                              |
| Reverse reachability | "can reach multiple destinations"         | `pacific-atlantic-water-flow`                  |

---

## 9. Dynamic Programming (1-D and 2-D)

This is the biggest cluster in your repo (12 problems) — worth the most memorization ROI.

### 9a. Linear 1-D DP — "decisions along a sequence"

**Signal:** "ways to reach N," "max/min ending here," decisions made left-to-right where each depends on a fixed window of previous states.

**Template (climbing stairs / house robber shape — 2-state rolling DP):**
```java
int prev2 = base0, prev1 = base1;
for (int i = 2; i <= n; i++) {
    int curr = f(prev1, prev2);   // e.g. prev1 + prev2, or max(prev1, prev2 + nums[i])
    prev2 = prev1;
    prev1 = curr;
}
return prev1;
```
**House Robber II variant:** circular array → run the linear version twice, once excluding the first house, once excluding the last, take the max. **Memorize this trick specifically** — "break the circle by trying both linear halves" reappears constantly.

**Coin change (unbounded knapsack shape):**
```java
int[] dp = new int[amount + 1];
Arrays.fill(dp, amount + 1);
dp[0] = 0;
for (int a = 1; a <= amount; a++)
    for (int coin : coins)
        if (coin <= a) dp[a] = Math.min(dp[a], dp[a - coin] + 1);
```
**Word break / decode ways (string-partition DP):**
```java
boolean[] dp = new boolean[s.length() + 1];
dp[0] = true;
for (int i = 1; i <= s.length(); i++)
    for (int j = 0; j < i; j++)
        if (dp[j] && dict.contains(s.substring(j, i))) { dp[i] = true; break; }
```
**Key idea:** define `dp[i]` in words first ("ways to break s[0..i)" / "min coins to make amount a") — the transition falls out naturally once the definition is precise.

**Repo problems:** `climbing-stairs`, `house-robber`, `house-robber-ii`, `coin-change`, `decode-ways`, `word-break`, `longest-increasing-subsequence`, `jump-game` (greedy/DP hybrid — track farthest reachable index)

### 9b. Kadane's Variant — "best subarray ending here"

**Template:**
```java
int curMax = nums[0], curMin = nums[0], result = nums[0];
for (int i = 1; i < n; i++) {
    if (nums[i] < 0) { int t = curMax; curMax = curMin; curMin = t; } // swap for product
    curMax = Math.max(nums[i], curMax + nums[i]);   // or * for product version
    curMin = Math.min(nums[i], curMin + nums[i]);
    result = Math.max(result, curMax);
}
```
**Key idea:** `maximum-subarray` only needs a running max. `maximum-product-subarray` needs a running **min** too, because a negative number can flip the smallest product into the largest.

**Repo problems:** `maximum-subarray`, `maximum-product-subarray`

### 9c. 2-D DP — "two sequences, or expand-around-center"

**Template (longest common subsequence):**
```java
int[][] dp = new int[m+1][n+1];
for (int i = 1; i <= m; i++)
    for (int j = 1; j <= n; j++)
        dp[i][j] = (a.charAt(i-1) == b.charAt(j-1))
            ? dp[i-1][j-1] + 1
            : Math.max(dp[i-1][j], dp[i][j-1]);
```
**Template (palindrome — expand around center, not a DP table):**
```java
void expand(String s, int l, int r) {
    while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) { l--; r++; }
    // valid palindrome is s[l+1 .. r-1]
}
// call expand(s, i, i) for odd-length centers, expand(s, i, i+1) for even-length
```
**Key idea:** `longest-palindromic-substring` and `palindromic-substrings` *look* like DP problems but the expand-around-center trick is O(n²) time / O(1) space and simpler than the DP table — recognize this shortcut so you don't overbuild a 2-D table under interview pressure.

**Repo problems:** `longest-common-subsequence`, `longest-palindromic-substring`, `palindromic-substrings`

---

## 10. Greedy / Interval Sorting

**Signal:** "schedule meetings," "minimum rooms," "can you attend all" — sort first, then sweep once.

**Template:**
```java
Arrays.sort(intervals, (a, b) -> a[0] - b[0]);  // sort by start time
for (int i = 1; i < intervals.length; i++)
    if (intervals[i][0] < intervals[i-1][1]) return false; // overlap
```
**Key idea:** almost every interval problem starts with "sort by start (or end) time" — that single line does most of the work.

**Repo problems:** `meeting-schedule`

---

## Quick Recall Table

| Pattern | Trigger phrase to listen for | Repo anchor problem |
|---|---|---|
| Hashing | "pair," "duplicate," "have I seen" | two-integer-sum |
| Stack | "matching," "nested," "valid sequence" | validate-parentheses |
| Binary search | "sorted array," O(log n) | find-minimum-in-rotated-sorted-array |
| Linked list | "reverse," "merge," in-place O(1) space | reverse-a-linked-list |
| Tree DFS | binary tree anything | invert-a-binary-tree |
| Heap | "top k," "running median" | find-median-in-a-data-stream |
| Backtracking | "all combinations/paths," grid search | combination-target-sum |
| Graph DFS/BFS | grid regions, dependencies, connectivity | course-schedule |
| 1-D DP | "ways to," "min/max ending at i" | house-robber |
| 2-D DP | two strings/sequences compared | longest-common-subsequence |
| Greedy/intervals | "schedule," "overlap," "min rooms" | meeting-schedule |

## How to use this for memorization

1. Cover the template, look only at the problem name — write the template from memory.
2. For each pattern, be able to say out loud in one sentence *why* that data structure/technique fits (the "signal" row) — that's what actually gets tested in an interview, recognizing the pattern from an unfamiliar problem statement, not reciting code.
3. Weakest clusters to drill again based on repo coverage: **Greedy/intervals** (only 1 problem) and **advanced graphs / Dijkstra / MST** (not represented at all — worth adding a couple of NeetCode's "Advanced Graphs" problems before an interview loop that's graph-heavy).



# Tutorial Reference
## What is this?

[NeetCode.io](https://neetcode.io) is a coding interview preparation platform featuring curated problems, video solutions, and an in-browser code editor. This repository is automatically populated with your accepted (or all) solutions using the **GitHub Sync** feature.

---

## How GitHub Sync works

1. **Connect your GitHub account** on [neetcode.io/profile/github](https://neetcode.io/profile/github).
2. **Auto-commit** — every time you submit a solution on NeetCode, it is pushed here automatically (configurable by status).
3. **Bulk Sync** — push all your past solutions at once from the GitHub settings page.
4. **Manual sync** — from the submission history panel on any problem page, sync or remove individual submissions.

---

## Repository structure

Solutions are organized by topic folder, then problem ID. Each submission is stored as a separate file:

```
<topic-folder>/
  <problem-id>/
    submission-0.<ext>   ← first submission
    submission-1.<ext>   ← second submission
    ...
```

**Example:**
```
Data Structures & Algorithms/two-integer-sum/submission-0.py
Data Structures & Algorithms/binary-search/submission-0.ts
Python For Beginners/python-hello-world/submission-0.py
```

---

## Supported languages

| Language | Extension |
|---|---|
| Python | `.py` |
| JavaScript | `.js` |
| TypeScript | `.ts` |
| Java | `.java` |
| C++ | `.cpp` |
| C# | `.cs` |
| Go | `.go` |
| Rust | `.rs` |
| Kotlin | `.kt` |
| Swift | `.swift` |
| SQL | `.sql` |

---

## Settings

Manage your sync preferences at [neetcode.io/profile/github](https://neetcode.io/profile/github):

- **Auto-commit toggle** — enable or disable automatic commits on submission
- **Status filter** — sync all submissions or accepted only
- **Rename repository** — rename this repo or start fresh with a new one
- **Bulk Sync** — push all past solutions at once (rate-limited)

---

*Generated by [NeetCode GitHub Integration](https://neetcode.io)*
