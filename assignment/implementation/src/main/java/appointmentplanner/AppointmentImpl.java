package appointmentplanner;

import appointmentplanner.api.*;

import java.time.Duration;
import java.time.Instant;

public class AppointmentImpl implements Appointment {

    private AppointmentRequest appointmentRequest;

    public AppointmentImpl(AppointmentRequest appointmentRequest) {
        this.appointmentRequest = appointmentRequest;
    }

    @Override
    public AppointmentRequest getRequest() {
        return this.appointmentRequest;
    }

    @Override
    public Duration getDuration() {
        return this.appointmentRequest.getDuration();
    }

    @Override
    public String getDescription() {
        return this.appointmentRequest.getDescription();
    }

    @Override
    public Priority getPriority() {
        return this.appointmentRequest.getPriority();
    }

    @Override
    public AppointmentData getAppointmentData() {
        return this.appointmentRequest.getAppointmentData();
    }

    @Override
    public Instant getStart() {
        return this.appointmentRequest.getStart(LocalDay.now());
    }

    @Override
    public Instant getEnd() {
        return null;
    }

    @Override
    public String toString() {
        return "";
    }
}
