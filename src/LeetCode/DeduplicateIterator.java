package LeetCode;

import java.util.*;

public class DeduplicateIterator<T> implements Iterator<T> {
    private final Iterator<T> it;
    private T next;

    public DeduplicateIterator(List<T> list) {
        it = list.iterator();
        next = null;
    }

    @Override
    public boolean hasNext() {
        return next != null || it.hasNext();
    }

    @Override
    public T next() {
        T cur;

        if (next != null) {
            cur = next;
            next = null;
        } else {
            cur = it.next();
        }

        while (it.hasNext()) {
            T val = it.next();
            if (val != cur) {
                next = val;
                break;
            }
        }

        return cur;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 3, 3, 4, 4);
        List<Integer> emptyList = new ArrayList<>();

        DeduplicateIterator<Integer> it = new DeduplicateIterator<>(list);
        DeduplicateIterator<Integer> emptyIt = new DeduplicateIterator<>(emptyList);

        while (it.hasNext()) {
            System.out.println(it.next());
        }

        while (emptyIt.hasNext()) {
            System.out.println(emptyIt.next());
        }
    }
}
