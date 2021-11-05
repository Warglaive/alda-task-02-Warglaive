package appointmentplanner;

import appointmentplanner.api.AppointmentData;
import appointmentplanner.api.AppointmentRequest;
import appointmentplanner.api.Priority;
import appointmentplanner.api.TimePreference;

import java.time.LocalTime;
import java.util.Objects;

public class AppointmentRequestImpl implements AppointmentRequest {
    private LocalTime startTime;
    private AppointmentData appointmentData;
    private TimePreference timePreference;

    public AppointmentRequestImpl(LocalTime startTime, AppointmentData appointmentData, TimePreference timePreference)
            throws IllegalArgumentException {
        if (/*startTime == null ||*/ appointmentData == null) {
            throw new IllegalArgumentException("Null values are not being accepted!");
        }
        if (timePreference == null) {
            timePreference = TimePreference.UNSPECIFIED;
        }

        this.startTime = startTime;
        this.appointmentData = appointmentData;
        this.timePreference = timePreference;
    }

    @Override
    public LocalTime getStartTime() {
        return this.startTime;
    }

    @Override
    public AppointmentData getAppointmentData() {
        return this.appointmentData;
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
    public TimePreference getTimePreference() {
        return timePreference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentRequestImpl that = (AppointmentRequestImpl) o;
        return startTime.equals(that.startTime) &&
                appointmentData.equals(that.appointmentData) &&
                timePreference == that.timePreference;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, appointmentData, timePreference);
    }
}
