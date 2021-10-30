package appointmentplanner;

import appointmentplanner.api.LocalDay;
import appointmentplanner.api.Timeline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

    @Test
    void getDay() {
        assertThat(this.localDayPlan.getDay()).isEqualTo(this.day);
    }


    @Test
    void earliest() {
        Instant expected = LocalDay.now().ofLocalTime(LocalTime.of(0, 0));
        Instant actual = this.localDayPlan.earliest();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void tooLate() {
        Instant expected = LocalDay.now().ofLocalTime(LocalTime.of(23, 59));
        Instant actual = this.localDayPlan.tooLate();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getTimeline() {
        assertThat(this.localDayPlan.getTimeline()).isExactlyInstanceOf(TimelineImpl.class);
    }
}
