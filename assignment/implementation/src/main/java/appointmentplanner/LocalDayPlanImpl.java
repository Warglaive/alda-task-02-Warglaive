package appointmentplanner;

import appointmentplanner.api.LocalDay;
import appointmentplanner.api.LocalDayPlan;
import appointmentplanner.api.Timeline;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class LocalDayPlanImpl implements LocalDayPlan {
    private Timeline timeline;
    private LocalDay localDay;

    public LocalDayPlanImpl(ZoneId zone, LocalDate date, Timeline timeline) {
        this.timeline = timeline;
        this.localDay = new LocalDay(zone, date);
    }

    public LocalDayPlanImpl(LocalDay localDay, Instant start, Instant end) {
        //Instant earliest = localDay.ofLocalTime(LocalTime.parse("00:00"));
        //Instant latest = localDay.ofLocalTime(LocalTime.parse("23:59"));

        if (/*start.isBefore(earliest) ||*/ start.isAfter(end) /*|| end.isAfter(latest)*/) {
            throw new IllegalArgumentException("Start has to be before end and positive. End has to be after start and positive. End has to be before 24.");
        }

        this.localDay = localDay;
        this.timeline = new TimeLineImpl(start,end);
    }

    @Override
    public LocalDay getDay() {
        return this.localDay;
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
