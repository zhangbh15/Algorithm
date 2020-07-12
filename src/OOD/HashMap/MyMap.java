package OOD.HashMap;

import java.util.*;

public final class MyMap<K, V> {
    private static class Cell<K, V> {
        private final K key;
        private V val;

        public Cell(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public K getKey() {
            return this.key;
        }
        public V getVal() {
            return this.val;
        }
        public void setVal(V val) {
            this.val = val;
        }
    }

    private static final double LOAD_FACTOR = 0.75d;
    private static final int DEFAULT_CAPACITY = 256;

    private int capacity;
    private int size;
    private List<Cell<K, V>>[] buckets;

    public MyMap() {
        this(DEFAULT_CAPACITY);
    }
    public MyMap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.buckets = (List<Cell<K, V>>[]) new List[capacity];
    }

    private int hashFunction(K key) {
        return key == null ? 0 : Math.abs(key.hashCode() % this.capacity);
    }

    public V put(K key, V val) {
        if (this.size >= this.capacity * LOAD_FACTOR) {
            rehashing();
        }

        int idx = hashFunction(key);
        if (this.buckets[idx] == null) {
            this.buckets[idx] = new ArrayList<>();
        }

        List<Cell<K, V>> bucket = this.buckets[idx];
        for (Cell<K, V> c : bucket) {
            if (Objects.equals(c.getKey(), key)) {
                V preVal = c.getVal();
                c.setVal(val);
                return preVal;
            }
        }

        bucket.add(new Cell<>(key, val));
        this.size++;
        return null;
    }

    public V get(K key) {
        int idx = hashFunction(key);
        List<Cell<K, V>> bucket = this.buckets[idx];
        if (bucket == null) {
            return null;
        }

        for (Cell<K, V> c : bucket) {
            if (Objects.equals(c.getKey(), key)) {
                return c.getVal();
            }
        }
        return null;
    }

    public boolean remove(K key) {
        int idx = hashFunction(key);
        List<Cell<K, V>> bucket = this.buckets[idx];
        if (bucket == null) {
            return false;
        }

        int size = bucket.size();
        for (int i = 0; i < size; i++) {
            if (Objects.equals(bucket.get(i).getKey(), key)) {
                Collections.swap(bucket, i, size - 1);
                bucket.remove(size - 1);
                this.size--;
                return true;
            }
        }
        return false;
    }

    private void rehashing() {
        this.capacity <<= 1;
        List<Cell<K, V>>[] newBuckets = (List<Cell<K, V>>[]) new List[this.capacity];
        for (List<Cell<K, V>> bucket : this.buckets) {
            if (bucket == null) {
                continue;
            }

            for (Cell<K, V> c : bucket) {
                int idx = hashFunction(c.getKey());
                if (newBuckets[idx] == null) {
                    newBuckets[idx] = new ArrayList<>();
                }
                newBuckets[idx].add(c);
            }
        }
        this.buckets = newBuckets;
    }

    public int size() {
        return this.size;
    }
}
