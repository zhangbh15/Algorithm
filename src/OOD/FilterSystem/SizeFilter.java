package OOD.FilterSystem;

import java.io.File;

public final class SizeFilter implements FilterBase {
    @Override
    public boolean filter(File file,FilterParameters fp) {
        return file.length() == fp.getSize();
    }
}
