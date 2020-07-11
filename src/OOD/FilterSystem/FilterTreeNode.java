package OOD.FilterSystem;

import java.io.File;

public abstract class FilterTreeNode {
    private final FilterCondition condition;
    private final FilterTreeOperator operator;
    private final FilterTreeNode left;
    private final FilterTreeNode right;

    public FilterTreeNode(FilterCondition condition) {
        this.condition = condition;
        this.operator = null;
        this.left = null;
        this.right = null;
    }

    public FilterTreeNode(FilterTreeOperator operator, FilterTreeNode left, FilterTreeNode right) {
        this.condition = null;
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public boolean eval(File file, FilterParameters fp) {
        if (this.condition != null) {
            return this.condition.filter(file, fp);
        }

        boolean left = this.left.eval(file, fp);
        boolean right = this.right.eval(file, fp);

        return this.operator.eval(left, right);
    }
}
