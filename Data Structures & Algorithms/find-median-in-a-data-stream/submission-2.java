class MedianFinder {

    PriorityQueue<Integer> minHeap = null;
    PriorityQueue<Integer> maxHeap = null;
    
    public MedianFinder() {
        //second half
        minHeap = new PriorityQueue<>();
        //first half
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }
    
    public void addNum(int num) {
        maxHeap.add(num);
        // rebalance
        minHeap.add(maxHeap.poll());
        // make sure maxHeap has extra , if any
        if(minHeap.size()>maxHeap.size())
            maxHeap.add(minHeap.poll());
    }
    
    public double findMedian() {
        //System.out.println("max : "+maxHeap.peek());
        //System.out.println("min : "+minHeap.peek());
        if(maxHeap.size()!=minHeap.size())
            return maxHeap.peek();
        else 
            return (maxHeap.peek()+minHeap.peek())/2.0;
    }
}
