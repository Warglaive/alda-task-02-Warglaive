package appointmentplanner;

import appointmentplanner.api.LocalDay;
import appointmentplanner.api.Timeline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

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
        //TODO: Check, may be buggy cos of Instant string representation of time
        String instantExpected = "2021-09-25T22:00:00Z";
        Clock clock = Clock.fixed(Instant.parse(instantExpected), ZoneId.of(this.zoneId.getId()));
        Instant expected = Instant.now(clock);
        Instant actual = this.localDayPlan.earliest();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void tooLate() {
        //TODO: Check, may be buggy cos of Instant string representation of time
        String instantExpected = "2021-09-26T21:59:59.999999999Z";
        Clock clock = Clock.fixed(Instant.parse(instantExpected), ZoneId.of(this.zoneId.getId()));
        Instant expected = Instant.now(clock);
        Instant actual = this.localDayPlan.tooLate();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getTimeline() {
        //TODO: Implement TimeLine
    }
}
