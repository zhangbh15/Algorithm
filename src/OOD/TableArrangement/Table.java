package OOD.TableArrangement;

public class Table {
    private final int size;
    private Customer customer;

    public Table(int size) {
        this.size = size;
        this.customer = null;
    }

    // return the remaining customers if table is not big enough
    public Customer assignCustomer(Customer customer) {
        if (this.customer != null) {
            return customer;
        }

        Customer ret = null;
        if (customer.getPartySize() > this.getSize()) {
            ret = new Customer(customer.getName(),
                    customer.getPartySize() - this.getSize());
        }

        this.customer = customer;
        return ret;
    }

    public void leaveCustomer() {
        this.customer = null;
    }

    public int getSize() {
        return this.size;
    }

    public Customer getCustomer() {
        return this.customer;
    }
}
