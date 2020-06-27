package OOD;
import java.util.*;

public final class ImmutableList<T> {
    private final List<T> list;

    @SafeVarargs
    public ImmutableList(T... a) { // T also has to be immutable
        this.list = Arrays.asList(a);
    }

    public T get(int i) {
        return list.get(i);
    }

    public int size() {
        return list.size();
    }
}
