class Solution {
    public int countComponents(int n, int[][] edges) {
            // create adj list
            //DFS on fisrt node
            //keep visited array of nodes.
            //for unvisited, pick from there.
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        
        for(int i=0;i<n;i++){
            lists.add(new ArrayList<Integer>());
        }

        for(int i=0;i<edges.length;i++){
                lists.get(edges[i][0]).add(edges[i][1]);
                lists.get(edges[i][1]).add(edges[i][0]);
            }
        
        boolean[] visited=new boolean[n];
        int count=0;
        for(int i=0;i<n;i++){
            if(!visited[i]){
                dfs(lists, visited, i);
                count++;
            }
        }
        return count;
    }

    public void dfs(List<List<Integer>> lists, boolean[] visited, int ind){
        if(ind==lists.size() || visited[ind])
            return;    
        List<Integer> neighbours = lists.get(ind);
        visited[ind]=true;
        for(int i=0;i<neighbours.size();i++){
            dfs(lists, visited, neighbours.get(i));
            visited[neighbours.get(i)]=true;
        }    
    }

}
