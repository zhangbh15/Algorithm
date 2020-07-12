package OOD.FilterSystem;

import java.io.File;

public final class NameFilter extends FilterCondition {
    public NameFilter(FilterParameters filterParam) {
        super(filterParam);
    }

    @Override
    public boolean filter(File file) {
        return file.getName().equals(this.filterParam.getName());
    }
}
