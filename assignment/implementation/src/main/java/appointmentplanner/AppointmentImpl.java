package appointmentplanner;

import appointmentplanner.api.*;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

public class AppointmentImpl implements Appointment {

    private AppointmentData appointmentData;
    private AppointmentRequest appointmentRequest;
    private TimeSlot timeSlot;

    public AppointmentImpl(AppointmentData appointmentData,
                           AppointmentRequest appointmentRequest,
                           TimeSlot timeSlot) throws IllegalArgumentException {
        if (appointmentData == null || appointmentRequest == null || timeSlot == null) {
            throw new IllegalArgumentException("Constructor args can NOT be NULL!");
        }
        this.appointmentData = appointmentData;
        this.appointmentRequest = appointmentRequest;
        this.timeSlot = timeSlot;
    }

    @Override
    public Duration getDuration() {
        return appointmentData.getDuration();
    }

    @Override
    public String getDescription() {
        return appointmentData.getDescription();
    }

    @Override
    public Priority getPriority() {
        return appointmentData.getPriority();
    }

    @Override
    public AppointmentData getAppointmentData() {
        return appointmentData;
    }

    @Override
    public AppointmentRequest getRequest() {
        return appointmentRequest;
    }

    @Override
    public Instant getStart() {
        return timeSlot.getStart();
    }

    @Override
    public Instant getEnd() {
        return timeSlot.getEnd();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentImpl that = (AppointmentImpl) o;
        return appointmentData.equals(that.appointmentData) &&
                appointmentRequest.equals(that.appointmentRequest) &&
                timeSlot.equals(that.timeSlot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentData, appointmentRequest, timeSlot);
    }
}
