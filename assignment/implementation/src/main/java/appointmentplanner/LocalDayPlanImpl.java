package appointmentplanner;

import appointmentplanner.api.LocalDay;
import appointmentplanner.api.LocalDayPlan;
import appointmentplanner.api.Timeline;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class LocalDayPlanImpl implements LocalDayPlan {

    //  private Instant instant = Instant.now(Clock.system(this.zone));
    /**
     * start time
     * and end time, which default to 0:00 (inclusive) and 24:00 exclusive.
     */
    // private LocalDateTime defaultStartLt = LocalDateTime.ofInstant(instant.plus(0, ChronoUnit.DAYS), ZoneId.systemDefault());
    // private Instant defaultStartInstant = this.defaultStartLt.toInstant(ZoneOffset.of(this.zone.getId()));
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


    public LocalDayPlanImpl(ZoneId zone, LocalDate date, Timeline timeline) {
        this.zone = zone;
        this.date = date;
        this.timeline = timeline;
        //get day from Date
        this.day = new LocalDay(zone, date);
        //get start Instant from LocalDay
        this.start = this.day.ofLocalTime(date.);
    }

    public LocalDayPlanImpl(LocalDay day, Instant start, Instant end) {
        this.day = day;
        this.start = start;
        this.end = end;
        //call other constructor
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
