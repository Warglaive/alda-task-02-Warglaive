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

  /*  private ZoneId zone;
    private LocalDate date;*/
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
        this.timeline = new TimelineImpl();
    }

    /*   public LocalDayPlanImpl(ZoneId zone, LocalDate date, Timeline timeline) {
     *//*   this.zone = zone;
        this.date = date;*//*
        this.timeline = timeline;
        //get day from Date
        this.day = new LocalDay(zone, date);
        this.start = LocalDay.now().ofLocalTime(LocalTime.of(0, 0));
        this.end = LocalDay.now().ofLocalTime(LocalTime.of(23, 0));
    }*/

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
