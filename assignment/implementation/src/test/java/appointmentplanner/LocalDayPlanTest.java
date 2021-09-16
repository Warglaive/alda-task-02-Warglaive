package appointmentplanner;

import appointmentplanner.api.LocalDay;
import appointmentplanner.api.Timeline;
import org.junit.jupiter.api.BeforeEach;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class LocalDayPlanTest {
    private LocalDayPlanImpl localDayPlan;
    private APFactory factory;

    /**
     * LocalDayPlan first constructor arguments
     */
    private LocalDay day;
    private Instant start;
    private Instant end;

    /**
     * LocalDayPLan second constructor arguments
     */
    private ZoneId zoneId;
    private LocalDate date;
    //TODO: IMPLEMENT
    private Timeline timeline;

    @BeforeEach
    void setUp() {
        this.factory = new APFactory();
        //init FIRST constructor arguments
        this.day = new LocalDay();
        //current local time NOW
        this.start = Instant.now();
        //15 mins
        int secsToMins = 900;
        this.end = Instant.now().plusSeconds(secsToMins);
        //init SECOND constructor arguments
        this.zoneId = this.day.getZone();
        this.date = this.day.getDate();
        this.timeline = new TimelineImpl();

        this.localDayPlan = (LocalDayPlanImpl) this.factory.createLocalDayPlan(this.zoneId, this.date, this.timeline);
    }
}
