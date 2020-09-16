package OOD.TableArrangement;

import java.util.*;

public class ArrangeSystem {
    private Table[] tables;
    private List<Table> availableTables; // ordered from small to big
    private Queue<Customer> waitingList;

    public ArrangeSystem(Table[] tables) {
        this.tables = tables;
        this.availableTables = new ArrayList<>();
        for (Table table : tables) {
            addAvailable(table);
        }
        waitingList = new LinkedList<>();
    }

    public void takeCustomer(Customer customer) {
        if (!this.availableTables.isEmpty()) {
            assignCustomer(customer);
            return;
        }

        waitingList.offer(customer);
    }

    public void leaveCustomer(Table table) {
        table.leaveCustomer();
        addAvailable(table);
        if (!this.waitingList.isEmpty()) {
            assignCustomer(waitingList.poll());
        }
    }


    // return remaining customers if no enough tables
    // customers can be split into smaller groups
    private void assignCustomer(Customer customer) {
        for (int i = 0; i < this.availableTables.size(); i++) {
            Table table = this.availableTables.get(i);
            if (table.getSize() >= customer.getPartySize()) {
                table.assignCustomer(customer);
                availableTables.remove(i);
                return;
            }
        }

        // No single table can seat all the customers, then assign from the largest table.
        Customer remain = this.availableTables.get(this.availableTables.size() - 1).assignCustomer(customer);
        this.availableTables.remove(this.availableTables.size() - 1);
        takeCustomer(remain);
    }

    private void addAvailable(Table table) {
        if (this.availableTables.size() == 0) {
            this.availableTables.add(table);
            return;
        }

        int start = 0;
        int end = this.availableTables.size() - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (this.availableTables.get(mid).getSize() < table.getSize()) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        this.availableTables.add(start, table);
    }
}
