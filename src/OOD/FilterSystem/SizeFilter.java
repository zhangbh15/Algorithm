package OOD.FilterSystem;

import java.io.File;

public final class SizeFilter extends FilterCondition {
    public SizeFilter(FilterParameters filterParam) {
        super(filterParam);
    }

    @Override
    public boolean filter(File file) {
        return file.length() == this.filterParam.getSize();
    }
}
