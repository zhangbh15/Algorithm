package OOD.FilterSystem;

import java.io.File;
import java.util.*;

public final class FileSystem {
    public List<File> filter(List<File> files, FilterBase filter, FilterParameters fp) {
        List<File> res = new LinkedList<>();
        if (files == null || files.size() == 0) {
            return res;
        }
        for (File f : files) {
            if (filter.filter(f, fp)) {
                res.add(f);
            }
        }
        return res;
    }

    public List<File> filter(List<File> files, FilterTreeNode root, FilterParameters fp) {
        List<File> res = new LinkedList<>();
        if (files == null || files.size() == 0) {
            return res;
        }

        for (File f : files) {
            if (root.eval(f, fp)) {
                res.add(f);
            }
        }
        return res;
    }
}
