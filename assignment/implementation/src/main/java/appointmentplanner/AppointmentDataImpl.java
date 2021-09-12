package appointmentplanner;

import appointmentplanner.api.AppointmentData;
import appointmentplanner.api.Priority;

import java.time.Duration;

public class AppointmentDataImpl implements AppointmentData {
    private String description;
    private Duration duration;
    private Priority priority;

    public AppointmentDataImpl(String description, Duration duration) {
        this.description = description;
        this.duration = duration;
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
}
