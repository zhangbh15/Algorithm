package OOD.FilterSystem;

import java.io.File;

public final class NameFilter implements FilterCondition {
    @Override
    public boolean filter(File file, FilterParameters fp) {
        return file.getName().equals(fp.getName());
    }
}
