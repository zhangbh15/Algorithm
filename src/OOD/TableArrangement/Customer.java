package OOD.TableArrangement;

public class Customer {
    private String name;
    private int partySize;

    public Customer(String name, int partySize) {
        this.name = name;
        this.partySize = partySize;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setPartySize(int partySize) {
        this.partySize = partySize;
    }
    public int getPartySize() {
        return this.partySize;
    }
}
