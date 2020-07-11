package OOD.FilterSystem;

import java.io.File;
import java.util.*;

/**
 * Given a list of files, filter them according to passed-in filter conditions and filter parameters.
 * Return a list of filtered files.
 */
public final class FileFilterSystem {
    // Single filter condition
    public List<File> filter(List<File> files, FilterCondition fc, FilterParameters fp) {
        List<File> res = new LinkedList<>();
        if (files == null || files.size() == 0) {
            return res;
        }

        for (File f : files) {
            if (fc.filter(f, fp)) {
                res.add(f);
            }
        }
        return res;
    }

    // Combo filter conditions
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
