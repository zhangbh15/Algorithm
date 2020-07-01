package OOD;

import java.lang.reflect.Constructor;
import java.util.*;

public class VendingMachine {
    private Map<String, Product> map;
    private Map<PaymentType, Payment> paymentMap;

    public boolean pay(String productId, PaymentType type, float amount) {
        Product product = map.get(productId);
        Payment payment = paymentMap.get(type);
        VMPayment vmp = new VMPayment(payment, product);
        return vmp.pay();
    }
}

class VMPayment {
    private Payment payment;
    private Product product;

    public VMPayment(Payment payment, Product product){
        this.payment = payment;
        this.product = product;
    }

    public boolean pay() {
        return payment.pay(product);
    }
}

enum PaymentType {
    CASH,
    COIN,
    CARD
}

interface Payment {
    boolean pay(Product product);
}

class CoinPayment implements Payment {
    @Override
    public boolean pay(Product product) {
        return false;
    }
}

class CashPayment implements Payment {
    @Override
    public boolean pay(Product product) {
        return false;
    }
}

class CardPayment implements Payment {
    @Override
    public boolean pay(Product product) {
        return false;
    }
}

class Product {
    private final String id;
    private final float price;
    private final String name;

    public Product(String id, float price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }
}