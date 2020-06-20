package OOD;

public class MinHeap {
    private Integer[] array;
    private int size;

    private static final int DEFAULT_CAPACITY = 8;

    public MinHeap(int capacity) {
        array = new Integer[capacity];
        size = 0;
    }

    public MinHeap() {
        this(DEFAULT_CAPACITY);
    }

    public MinHeap(Integer[] array) {
        this.array = array;
        heapify();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void offer(int val) {
        if (size >= array.length) {
            extend();
        }

        array[size] = val;
        percolateUp(size++);
    }

    public Integer poll() {
        try {
            if (size == 0) {
                throw new RuntimeException("The heap is empty.");
            }

            int ret = array[0];
            array[0] = array[size - 1];
            percolateDown(0);
            size--;
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer peek() {
        try {
            if (size == 0) {
                throw new RuntimeException("The heap is empty.");
            }

            return array[0];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer remove(int val) { // assume exists duplicate and remove all of them
        Integer ret = null;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(val)) {
                ret = array[i];
                array[i] = array[size - 1];
                if (array[(i - 1) / 2].compareTo(array[i]) > 0) {
                    percolateUp(i);
                } else {
                    percolateDown(i);
                }
            }
        }

        return ret;
    }

    public void set(int idx, int val) {
        if (idx < 0 || idx >= size) {
            return;
        }

        Integer temp = array[idx];
        array[idx] = val;
        if (temp.compareTo(val) >= 0) {
            percolateUp(idx);
        } else {
            percolateDown(idx);
        }
    }

    public int size() {
        return size;
    }

    public void heapify() {
        for (int i = (size - 2) / 2; i >= 0; i--) {
            percolateDown(i);
        }
    }

    private void percolateDown(int idx) {
        int left = idx * 2 + 1;
        int right = left + 1;

        if (left < size && array[left].compareTo(array[idx]) < 0
                && array[left].compareTo(array[right]) <= 0) {
            swap(idx, left);
            percolateDown(left);
        } else if (right < size && array[right].compareTo(array[idx]) < 0) {
            swap(idx, right);
            percolateDown(right);
        }
    }

    private void percolateUp(int idx) {
        if (idx == 0) {
            return;
        }

        int parent = (idx - 1) / 2;
        if (array[parent].compareTo(array[idx]) > 0) {
            swap(parent, idx);
            percolateUp(parent);
        }
    }

    private void extend() {
        Integer[] newArray = new Integer[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        MinHeap heap = new MinHeap(8);
        for (int i = 0; i < 10; i++) {
            heap.offer(i * 2);
            heap.offer(10 - i);
        }

        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
    }
}
