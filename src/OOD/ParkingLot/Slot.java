package OOD.ParkingLot;

public class Slot {
    private String id;
    private Vehicle vehicle;
    private Level level;

    public boolean isAvailable() {
        return this.vehicle == null;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (this.vehicle != null) {
            return false;
        }

        this.vehicle = vehicle;
        this.level.park();
        return true;
    }

    public void leaveVehicle() {
        this.vehicle = null;
        this.level.leave();
    }
}
