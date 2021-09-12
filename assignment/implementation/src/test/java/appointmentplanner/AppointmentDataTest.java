package appointmentplanner;

import appointmentplanner.api.Priority;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class AppointmentDataTest {
    private String description = "test";
    private Duration duration = Duration.ofHours(2);
    private Priority priority = Priority.HIGH;


    APFactory factory;
    AppointmentDataImpl appointmentDataWithPriority;
    AppointmentDataImpl appointmentData;


    @BeforeEach
    void setUp() {
        this.factory = new APFactory();
        this.appointmentDataWithPriority = (AppointmentDataImpl) this.factory.createAppointmentData(this.description, this.duration, this.priority);
        this.appointmentData = (AppointmentDataImpl) this.factory.createAppointmentData(this.description, this.duration);
    }

    @Test
    void ctorPriorityTest() {
        SoftAssertions.assertSoftly(x -> {
            assertThat(this.appointmentDataWithPriority).isExactlyInstanceOf(AppointmentDataImpl.class);
            assertThat(this.appointmentData).isExactlyInstanceOf(AppointmentDataImpl.class);
        });
    }

    @Test
    void getDurationTest() {
        Duration expectedDuration = Duration.ofHours(2);
        assertThat(this.appointmentDataWithPriority.getDuration()).isEqualTo(expectedDuration);
    }

    @Test
    void getDescriptionTest() {
        String expectedDesc = "test";
        assertThat(this.appointmentDataWithPriority.getDescription()).isEqualTo(expectedDesc);
    }

    @Test
    void getPriorityTest() {
        Priority expectedPriority = Priority.HIGH;
        assertThat(this.appointmentDataWithPriority.getPriority()).isEqualTo(expectedPriority);
    }
}
