package appointmentplanner;

import appointmentplanner.api.LocalDay;
import appointmentplanner.api.LocalDayPlan;
import appointmentplanner.api.Timeline;

import java.time.*;

public class LocalDayPlanImpl implements LocalDayPlan {

    private Timeline timeline;
    private LocalDay day;

    public LocalDayPlanImpl(ZoneId zone, LocalDate date, Timeline timeline) {
        this.timeline = timeline;
        this.day = new LocalDay(zone, date);
    }

    public LocalDayPlanImpl(LocalDay localDay, Instant start, Instant end) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("'Start' can not be after 'End'");
        }

        this.day = localDay;
        this.timeline = new TimelineImpl(start, end);
    }

    @Override
    public LocalDay getDay() {
        return this.day;
    }

    @Override
    public Instant earliest() {
        return this.timeline.start();
    }

    @Override
    public Instant tooLate() {
        return this.timeline.end();
    }

    @Override
    public Timeline getTimeline() {
        return this.timeline;
    }
}
