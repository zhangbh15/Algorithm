package OOD.ParkingLot;

import java.util.List;

public class Vehicle {
    private String id;
    private VehicleType type;
    private List<Slot> parkedAt;
    private Ticket ticket;

    public boolean park(List<Slot> slots) {
        for (Slot slot : slots) {
            if (!slot.isAvailable()) {
                return false;
            }
        }

        for (Slot slot : slots) {
            this.parkedAt.add(slot);
            slot.parkVehicle(this);
        }
        return true;
    }

    public long leave() {
        for (int i = 0; i < this.parkedAt.size(); i++) {
            this.parkedAt.remove(i).leaveVehicle();
        }
        return System.currentTimeMillis() - this.ticket.getArrivedAt();
    }
}


