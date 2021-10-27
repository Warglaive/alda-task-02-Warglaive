package appointmentplanner;

import appointmentplanner.api.TimeSlot;

import java.time.Instant;

public class TimeSlotImpl implements TimeSlot {
    private Instant start;
    private Instant end;

    public TimeSlotImpl(Instant start, Instant end) {
        this.start = start;
        this.end = end;
    }

    public void setStart(Instant start) {
        this.start = start;
    }

    public void setEnd(Instant end) {
        this.end = end;
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

    @Override
    public Instant getStart() {
        if (this.start != null) {
            return this.getStart();
        }
        throw new NullPointerException("TimeSlot.appointment.getStart() is Null, line 29");
    }

    @Override
    public Instant getEnd() {
        if (this.end != null) {
            return this.getEnd();
        }
        throw new NullPointerException("TimeSlot.appointment.getEnd() is Null, line 38");
    }
}
