package appointmentplanner;

import appointmentplanner.api.TimeSlot;

import java.time.Instant;

public class TimeSlotImpl implements TimeSlot {
    private AppointmentImpl appointment;

    /**
     * Add Appointment
     *
     * @return
     */

    @Override
    public Instant getStart() {
        return this.appointment.getStart();
    }

    @Override
    public Instant getEnd() {
        return this.appointment.getEnd();
    }

    @Override
    public String toString() {
        //TODO: IMPLEMENT
        return "";
    }
}
