package appointmentplanner;

import appointmentplanner.api.TimeSlot;

import java.time.Instant;
import java.util.Objects;

public class TimeSlotImpl implements TimeSlot {
    private Instant startTime;
    private Instant endTime;

    public TimeSlotImpl(Instant startTime, Instant endTime) throws IllegalArgumentException {
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("Null values are not being accepted!");
        }
        if (endTime.isBefore(startTime) /*|| startTime.equals(endTime)*/) {
            throw new IllegalArgumentException("End must lie after start");
        }

        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public Instant getStart() {
        return this.startTime;
    }

    @Override
    public Instant getEnd() {
        return this.endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlotImpl timeslot = (TimeSlotImpl) o;
        return startTime.equals(timeslot.startTime) &&
                endTime.equals(timeslot.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }

    /**
     * The implementer should implement a proper to string showing start instant,
     * end instant time and duration of this slot.
     *
     * @return
     */
    @Override
    public String toString() {
        return "Start: " + this.getStart() + "End:  " + this.getEnd() + "Duration: " + this.duration();
    }
}
