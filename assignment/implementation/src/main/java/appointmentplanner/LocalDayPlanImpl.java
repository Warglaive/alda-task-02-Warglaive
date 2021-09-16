package appointmentplanner;

import appointmentplanner.api.LocalDay;
import appointmentplanner.api.LocalDayPlan;
import appointmentplanner.api.Timeline;

import java.time.Instant;

public class LocalDayPlanImpl implements LocalDayPlan {

    private LocalDay day;
    private Instant start;
    private Instant end;

    public LocalDayPlanImpl(LocalDay day, Instant start, Instant end) {
        this.day = day;
        this.start = start;
        this.end = end;
    }

    @Override
    public LocalDay getDay() {
        return this.day;
    }

    @Override
    public Instant earliest() {
        return null;
    }

    @Override
    public Instant tooLate() {
        return null;
    }

    @Override
    public Timeline getTimeline() {
        return null;
    }
}
