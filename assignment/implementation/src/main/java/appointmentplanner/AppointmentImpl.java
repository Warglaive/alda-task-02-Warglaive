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
        return LocalDay.now().ofLocalTime(this.appointmentRequest.getStartTime());
    }

    @Override
    public Instant getEnd() {
        return LocalDay.now().ofLocalTime(this.appointmentRequest.getStartTime()).plus(this.appointmentRequest.getDuration());
    }

    /**
     * You should override toString(). toString() returns startTime,
     * * endTime, description and priority like: "2019-09-12 14:00 - 15:55 ALDA Lesson
     * * (HIGH)". This will make your testing and debugging live so much easier.
     *
     * @return
     */
    @Override
    public String toString() {
        //TODO: Implement
        return "Start Time: " + this.getStart() + "End Time: " + "Description: " + this.getDescription() + "Priority: " + this.getPriority();
    }
}
