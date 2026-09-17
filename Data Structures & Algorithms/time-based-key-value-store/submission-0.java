class TimeMap {

    HashMap<String, List<Pair>> map;
    
    public static class Pair{
        String value;
        int timestamp;

        public Pair(String value, int timestamp){
            this.value=value;
            this.timestamp=timestamp;
        }
    }

    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        Pair pair=new Pair(value, timestamp);
        map.computeIfAbsent(key, k-> new ArrayList<Pair>())
        .add(pair);
    }
    
    public String get(String key, int timestamp) {
        if(!map.containsKey(key)){
            return "";
        }
        List<Pair> list = map.get(key);
        int l=0,h=list.size()-1;
        String res="";
        while(l<=h){
            int mid=(l+h)/2;
            if(timestamp>=list.get(mid).timestamp){
                res=list.get(mid).value;
                l=mid+1;
            } else {
                h=mid-1;
            }
        }
        return res;
    }
}
