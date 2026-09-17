class LRUCache {

LinkedHashMap<Integer, Integer> map;
int cap;

    public LRUCache(int capacity) {
        map= new LinkedHashMap<>(capacity);
        this.cap=capacity;
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        int val=map.get(key);
        map.remove(key);
        map.put(key, val);
        return val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            map.remove(key);
        }
        map.put(key, value);
        if(map.size()>cap){
            map.remove(map.entrySet().iterator().next().getKey());
        }
     }
}
