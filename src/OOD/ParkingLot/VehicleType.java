package OOD.ParkingLot;

public enum VehicleType {
    MOTOR(1),
    SEDAN(2),
    TRUCK(3);

    private final int size;

    VehicleType(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }
}
