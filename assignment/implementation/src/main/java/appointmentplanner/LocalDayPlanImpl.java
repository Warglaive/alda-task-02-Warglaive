package appointmentplanner;

import appointmentplanner.api.LocalDay;
import appointmentplanner.api.LocalDayPlan;
import appointmentplanner.api.Timeline;

import java.time.*;

public class LocalDayPlanImpl implements LocalDayPlan {

    /**
     * start time
     * and end time, which default to 0:00 (inclusive) and 24:00 exclusive.
     */
    /**
     * first constructor arguments
     */

    private ZoneId zone;
    private LocalDate date;
    private Timeline timeline;
    /**
     * second constructor arguments
     */
    private LocalDay day;
    private Instant start;
    private Instant end;

    public LocalDayPlanImpl(LocalDay day, Instant start, Instant end) {
        this.day = day;
        this.start = start;
        this.end = end;
    }

    public LocalDayPlanImpl(ZoneId zone, LocalDate date, Timeline timeline) {
        this.zone = zone;
        this.date = date;
        this.timeline = timeline;
        //get day from Date
        this.day = new LocalDay(zone, date);
        //pass date and get start Instant from LocalDay
        //get start time (00:00) for date because hardcoding stuff is bad, no?
        LocalTime startTime = date.atStartOfDay().toLocalTime();
        //get end time (23:59) for date because hardcoding stuff is bad, no?
        LocalDateTime now = LocalDateTime.now(); // 2015-11-19T19:42:19.224 - FORMAT EXAMPLE
        LocalTime endTime = date.atTime(now.with(LocalTime.MAX).toLocalTime()).toLocalTime();
        this.start = this.day.ofLocalTime(startTime);

        this.end = this.day.ofLocalTime(endTime);
    }

    @Override
    public LocalDay getDay() {
        return this.day;
    }

    @Override
    public Instant earliest() {
        return this.start;
    }

    @Override
    public Instant tooLate() {
        return this.end;
    }

    @Override
    public Timeline getTimeline() {
        return this.timeline;
    }
}
