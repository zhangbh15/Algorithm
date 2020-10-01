package OOD.VendingMachine;

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


    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();
        vm.pay("product1", PaymentType.CARD, 88.8);
    }
}

