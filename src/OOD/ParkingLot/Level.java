package OOD.ParkingLot;

import java.util.List;

public class Level {
    private String id;
    private int totalCapacity;
    private int availableCapacity;
    private List<Slot> slots;
    private ParkingLot parkingLot;

    public void park() {
        // The logic of other classes guarantees the
        // availableCapacity won't exceed the limit.
        this.availableCapacity--;
    }

    public void leave() {
        this.availableCapacity++;
    }

    public int getAvailableCapacity() {
        return this.availableCapacity;
    }
}
