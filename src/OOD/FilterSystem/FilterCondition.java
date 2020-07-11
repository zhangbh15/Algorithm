package OOD.FilterSystem;

import java.io.File;

public interface FilterCondition {
    boolean filter(File file, FilterParameters fp);
}
