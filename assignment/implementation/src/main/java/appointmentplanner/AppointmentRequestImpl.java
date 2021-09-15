package appointmentplanner;

import appointmentplanner.api.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;

public class AppointmentRequestImpl implements AppointmentRequest {
    private AppointmentData appData;
    private LocalTime prefStart;
    private TimePreference fallBack;

    public AppointmentRequestImpl(AppointmentData appData, LocalTime prefStart, TimePreference fallBack) {
        this.appData = appData;
        this.prefStart = prefStart;
        this.fallBack = fallBack;
    }

    @Override
    public Instant getStart(LocalDay onDay) {
        return AppointmentRequest.super.getStart(onDay);
    }

    @Override
    public LocalTime getStartTime() {
        return this.prefStart;
    }

    @Override
    public AppointmentData getAppointmentData() {
        return this.appData;
    }

    @Override
    public TimePreference getTimePreference() {
        return AppointmentRequest.super.getTimePreference();
    }

    @Override
    public Duration getDuration() {
        return AppointmentRequest.super.getDuration();
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Priority getPriority() {
        return null;
    }
}
