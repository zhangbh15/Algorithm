package OOD.FilterSystem;

public final class AndFilterTreeOperator implements FilterTreeOperator {
    @Override
    public boolean eval(boolean left, boolean right) {
        return left && right;
    }
}
