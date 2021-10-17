package appointmentplanner;

import appointmentplanner.api.AppointmentRequest;

import java.time.Instant;

public class TimeSlotImpl extends AppointmentImpl {
    private AppointmentRequest appointmentRequest;

    public TimeSlotImpl(AppointmentRequest appointmentRequest) {
        super(appointmentRequest);
        this.appointmentRequest = appointmentRequest;
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
        return "Start: " + this.getStart() + "End:  " + this.getEnd() + "Duration: " + this.duration();
    }

    @Override
    public Instant getStart() {
        return super.getStart();
    }

    @Override
    public Instant getEnd() {
        return super.getEnd();
    }
}
