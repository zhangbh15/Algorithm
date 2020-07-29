package OOD.ParkingLot;

import java.util.Date;

public class Ticket {
    private final long id;
    private final long arrivedAt;
    private final Vehicle vehicle;
    private final ParkingLot parkingLot;

    private static long curId = 0;

    public Ticket(Vehicle vehicle, ParkingLot parkingLot) {
        this.id = curId++;
        this.arrivedAt = System.currentTimeMillis();
        this.vehicle = vehicle;
        this.parkingLot = parkingLot;
    }

    public long getArrivedAt() {
        return this.arrivedAt;
    }

    public ParkingLot getParkingLot() {
        return this.parkingLot;
    }
}
