package appointmentplanner;

import appointmentplanner.api.AppointmentData;
import appointmentplanner.api.Priority;

import java.time.Duration;
import java.util.Objects;

public class AppointmentDataImpl implements AppointmentData {
    private Duration duration;
    private String description;
    private Priority priority;

    public AppointmentDataImpl(Duration duration, String description, Priority priority)
            throws IllegalArgumentException {

        if (duration == null || description == null || priority == null || description.trim().equals("")) {
            throw new IllegalArgumentException("Null values are not being accepted!");
        }
        if (duration.getSeconds() < 0) {
            throw new IllegalArgumentException("Invalid duration, please give a positive value!");
        }

        this.duration = duration;
        this.description = description;
        this.priority = priority;
    }

    public AppointmentDataImpl(Duration duration, Priority priority) throws IllegalArgumentException {
        this(duration, "No description", priority);
    }

    public AppointmentDataImpl(Duration duration, String description) {
        this(duration, description, Priority.LOW);
    }

    public AppointmentDataImpl(String description, Duration duration) {
        this.description = description;
        this.duration = duration;
        this.priority = Priority.LOW;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentDataImpl that = (AppointmentDataImpl) o;
        return Objects.equals(duration, that.duration) &&
                Objects.equals(description, that.description) &&
                priority == that.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration, description, priority);
    }
}
