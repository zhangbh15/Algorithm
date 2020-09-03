package OOD.VendingMachine;

public final class CoinPayment implements Payment {
    @Override
    public boolean pay(Product product, double value) {
        // process coin payment
        return value >= product.getPrice();
    }
}
