package OOD;

import java.lang.reflect.Constructor;
import java.util.*;

public class VendingMachine {
    private final Map<String, Product> idToProduct;

    public VendingMachine() {
        idToProduct = new HashMap<>();
    }


    public boolean pay(String productId, PaymentType paymentType, double value) {
        Product product = idToProduct.get(productId);
        Payment payment = PaymentFactory.getPayment(paymentType);
        return payment.pay(product, value);
    }


    public void addProduct(String id, Product product) {
        idToProduct.put(id, product);
    }
    public void removeProduct(String id) {
        idToProduct.remove(id);
    }
}

final class PaymentFactory {
    public static Payment getPayment(PaymentType paymentType) {
        switch (paymentType) {
            case CASH: return new CashPayment();
            case COIN: return new CoinPayment();
            case CARD: return new CardPayment();
            default: throw new IllegalArgumentException("Unsupported payment type.");
        }
    }
}


enum PaymentType {
    CASH,
    COIN,
    CARD
}

interface Payment {
    boolean pay(Product product, double value);
}

final class CashPayment implements Payment {
    @Override
    public boolean pay(Product product, double value) {
        // process cash payment
        return value >= product.getPrice();
    }
}

final class CoinPayment implements Payment {
    @Override
    public boolean pay(Product product, double value) {
        // process coin payment
        return value >= product.getPrice();
    }
}

final class CardPayment implements Payment {
    @Override
    public boolean pay(Product product, double value) {
        // process card payment
        return value >= product.getPrice();
    }
}

final class Product {
    private final String id;
    private final double price;
    private final String name;

    public Product(String id, float price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}