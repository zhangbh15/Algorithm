package OOD.FilterSystem;

public class AndFilterTreeOperator implements FilterTreeOperator {
    @Override
    public boolean eval(boolean left, boolean right) {
        return left && right;
    }
}
