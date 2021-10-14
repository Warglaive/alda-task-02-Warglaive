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

    /**
     * TimeSlot
     *
     * @return
     */
    @Override
    public Instant getStart() {
        //Get start for appointment day and return as Instant
        //Get LocalDay from LocalDayPlanImpl
        /*var a = new LocalDayPlanImpl();
        var a = this.appointmentRequest.getStart( );
        return a;*/
    }

    /**
     * TimeSlot
     *
     * @return
     */
    @Override
    public Instant getEnd() {
        return null;
    }
}
