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
        assertThat(this.appointmentData).isExactlyInstanceOf(AppointmentDataImpl.class);
    }

    @Test
    void getDurationTest() {
        Duration expectedDuration = Duration.ofHours(2);
        assertThat(this.appointmentData.getDuration()).isEqualTo(expectedDuration);
    }

    @Test
    void getDescriptionTest() {
        String expectedDesc = "test";
        assertThat(this.appointmentData.getDescription()).isEqualTo(expectedDesc);
    }

    @Test
    void getPriorityTest() {
        Priority expectedPriority = Priority.HIGH;
        assertThat(this.appointmentData.getPriority()).isEqualTo(expectedPriority);
    }
}
