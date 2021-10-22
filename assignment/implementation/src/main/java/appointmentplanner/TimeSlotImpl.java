package appointmentplanner;

import appointmentplanner.api.Appointment;
import appointmentplanner.api.TimeSlot;

import java.time.Instant;

public class TimeSlotImpl implements TimeSlot {
    private Appointment appointment;

    public TimeSlotImpl(Appointment appointment) {
      //  super(appointment);
        this.appointment = appointment;
    }

    /**
     * The implementer should implement a proper to string showing start instant,
     * end instant time and duration of this slot.
     *
     * @return
     */
    @Override
    public String toString() {
        //TODO: IMPLEMENT
        return "Start: " + this.getStart() + "End:  " + this.getEnd() + "Duration: " + appointment.getDuration();
    }

    @Override
    public Instant getStart() {
        if (this.appointment.getStart()!= null) {
            return this.getStart();
        }
        throw new NullPointerException("TimeSlot.appointment.getStart() is Null, line 29");
    }

    @Override
    public Instant getEnd() {
        if (this.appointment.getEnd()!= null) {
            return this.getEnd();
        }
        throw new NullPointerException("TimeSlot.appointment.getEnd() is Null, line 38");    }
}
