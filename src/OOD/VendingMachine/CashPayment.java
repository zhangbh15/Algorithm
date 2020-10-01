package OOD.VendingMachine;

public final class CashPayment implements Payment {
    @Override
    public boolean pay(Product product, double value) {
        // process cash payment
        return value >= product.getPrice();
    }
}
