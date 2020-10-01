package OOD.VendingMachine;

public final class CardPayment implements Payment {
    @Override
    public boolean pay(Product product, double value) {
        // process card payment
        return value >= product.getPrice();
    }
}
