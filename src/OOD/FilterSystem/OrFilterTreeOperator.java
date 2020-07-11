package OOD.FilterSystem;

public final class OrFilterTreeOperator implements FilterTreeOperator {
    @Override
    public boolean eval(boolean left, boolean right) {
        return left || right;
    }
}
