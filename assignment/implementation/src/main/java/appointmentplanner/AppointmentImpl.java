package appointmentplanner;

import appointmentplanner.api.Appointment;
import appointmentplanner.api.AppointmentData;
import appointmentplanner.api.AppointmentRequest;
import appointmentplanner.api.Priority;

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
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Priority getPriority() {
        return null;
    }

    @Override
    public AppointmentData getAppointmentData() {
        return null;
    }



    @Override
    public Instant getStart() {
        return null;
    }

    @Override
    public Instant getEnd() {
        return null;
    }
}
