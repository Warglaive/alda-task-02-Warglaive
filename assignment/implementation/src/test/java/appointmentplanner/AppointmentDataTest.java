package appointmentplanner;

import appointmentplanner.api.Priority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.TemporalAmount;

import static org.assertj.core.api.Assertions.assertThat;

public class AppointmentDataTest {
    private String description = "test";
    //2 hrs duration
    private Duration duration = Duration.ofHours(2);
    private Priority priority = Priority.HIGH;


    AppointmentDataImpl appointmentData;

    @BeforeEach
    void setUp() {
        this.appointmentData = new AppointmentDataImpl(this.description, this.duration, this.priority);
    }

    @Test
    void ctorTest() {
        String description = "test";
        //2 hrs duration
        Duration duration = Duration.ofHours(2);
        Priority priority = Priority.HIGH;

        assertThat(new AppointmentDataImpl(description, duration, priority)).isExactlyInstanceOf(AppointmentDataImpl.class);
    }

    @Test
    void getDurationTest() {
        Duration expectedDuration = Duration.ofHours(2);
         assertThat(this.appointmentData.getDuration()).isEqualTo(expectedDuration);
    }
}
