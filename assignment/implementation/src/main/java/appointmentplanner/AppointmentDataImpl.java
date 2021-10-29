package appointmentplanner;

import appointmentplanner.api.AppointmentData;
import appointmentplanner.api.Priority;

import java.time.Duration;

public class AppointmentDataImpl implements AppointmentData {
    private String description;
    private Duration duration;
    private Priority priority;
    private final Priority defaultPriority = Priority.LOW;

    public AppointmentDataImpl(String description, Duration duration) {
        this.description = description;
        this.duration = duration;
        this.priority = this.defaultPriority;
    }


    public AppointmentDataImpl(String description, Duration duration, Priority priority) {
        this.description = description;
        this.duration = duration;
        this.priority = priority;
    }

    @Override
    public Duration getDuration() {
        return this.duration;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Priority getPriority() {
        return this.priority;
    }

    @Override
    public String toString() {
        return "AppointmentDataImpl{" +
                "description='" + description + '\'' +
                ", duration=" + duration +
                ", priority=" + priority +
                ", defaultPriority=" + defaultPriority +
                '}';
    }
}
