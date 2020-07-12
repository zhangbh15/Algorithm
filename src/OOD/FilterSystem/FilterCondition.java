package OOD.FilterSystem;

import java.io.File;

public abstract class FilterCondition {
    protected FilterParameters filterParam;
    public FilterCondition(FilterParameters filterParam) {
        this.filterParam = filterParam;
    }

    abstract boolean filter(File file);
}
