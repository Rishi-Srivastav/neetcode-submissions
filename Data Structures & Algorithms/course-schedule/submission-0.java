class Solution {

    public boolean canFinish(int numCourses, int[][] pre) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] p : pre) {
            int course = p[0];
            int prereq = p[1];

            graph.get(prereq).add(course);
        }

        int[] state = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0 && hasCycle(i, state, graph)) {
                return false;
            }
        }

        return true;
    }

    private boolean hasCycle(
            int node,
            int[] state,
            List<List<Integer>> graph) {

        if (state[node] == 1)
            return true;

        if (state[node] == 2)
            return false;

        state[node] = 1;

        for (int neighbor : graph.get(node)) {
            if (hasCycle(neighbor, state, graph)) {
                return true;
            }
        }

        state[node] = 2;

        return false;
    }
}