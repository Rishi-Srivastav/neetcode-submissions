class Solution {

    public boolean validTree(int n, int[][] edges) {

        // A tree with n nodes must have exactly n - 1 edges
        if (edges.length != n - 1) {
            return false;
        }

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int node = edge[0];
            int neighbor = edge[1];

            graph.get(node).add(neighbor);
            graph.get(neighbor).add(node);
        }

        boolean[] visited = new boolean[n];

        if (hasCycle(0, -1, visited, graph)) {
            return false;
        }

        // Make sure graph is connected
        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }

        return true;
    }

    private boolean hasCycle(
            int node,
            int parent,
            boolean[] visited,
            List<List<Integer>> graph) {

        visited[node] = true;

        for (int neighbor : graph.get(node)) {

            // Going back to parent is expected in undirected graph
            if (neighbor == parent) {
                continue;
            }

            // Visited node other than parent → cycle
            if (visited[neighbor]) {
                return true;
            }

            if (hasCycle(neighbor, node, visited, graph)) {
                return true;
            }
        }

        return false;
    }
}