package appointmentplanner;

import appointmentplanner.api.AppointmentData;
import appointmentplanner.api.LocalDay;
import appointmentplanner.api.Priority;
import appointmentplanner.api.TimePreference;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AppointmentRequestTest {
    AppointmentRequestImpl appointmentRequest;
    /**
     * AppointmentData constructor arguments
     */
    private String description = "test";
    private Duration duration = Duration.ofHours(2);
    private Priority priority = Priority.HIGH;
    /**
     * AppointmentRequest constructor arguments
     */

    private APFactory factory;
    private AppointmentData appData;
    private LocalTime prefStart;
    private TimePreference fallBack;

    @BeforeEach
    void setUp() {
        this.factory = new APFactory();
        this.prefStart = LocalTime.of(14, 20);
        this.fallBack = TimePreference.EARLIEST;

        this.appData = this.factory.createAppointmentData(this.description, this.duration);
        this.appointmentRequest = (AppointmentRequestImpl) factory.createAppointmentRequest(this.appData, this.prefStart, this.fallBack);
    }

    @Test
    void fieldTest() {
        SoftAssertions.assertSoftly(s -> {
            assertThat(this.appointmentRequest).hasFieldOrProperty("description");
            assertThat(this.appointmentRequest).hasFieldOrProperty("duration");
            assertThat(this.appointmentRequest).hasFieldOrProperty("priority");

            assertThat(this.appointmentRequest).hasFieldOrProperty("appData");
            assertThat(this.appointmentRequest).hasFieldOrProperty("prefStart");
            assertThat(this.appointmentRequest).hasFieldOrProperty("fallBack");
        });
    }

    @Test
    void constructorTest() {
        assertThat(this.appointmentRequest).isExactlyInstanceOf(AppointmentRequestImpl.class);
    }

    @Test
    void getStartOnDayTest() {
        var expected = LocalDay.now().at(14, 20);
        assertThat(this.appointmentRequest.getStart(LocalDay.now())).isEqualTo(expected);
    }

    @Test
    void getStartTimeTest() {
        assertThat(this.appointmentRequest.getStartTime()).isEqualTo(LocalTime.of(14, 20));
    }

    @Test
    void getAppDataTest() {
        assertThat(this.appointmentRequest.getAppointmentData()).isEqualTo(this.appointmentRequest.getAppointmentData());
    }

    @Test
    void getTimePreference() {
        var expectedTimePreference = TimePreference.EARLIEST;
        assertThat(this.appointmentRequest.getTimePreference()).isEqualTo(expectedTimePreference);
    }

    @Test
    void getDurationTest() {
        assertThat(this.appointmentRequest.getAppointmentData().getDuration()).isEqualTo(this.duration);
    }

    @Test
    void getDescription() {
        assertThat(this.appointmentRequest.getAppointmentData().getDescription()).isEqualTo(this.description);
    }

    /*@Test
    void getPriorityNull() {
        assertThat(this.appointmentRequest.getAppointmentData().getPriority()).isNull();
    }*/

    @Test
    void getPriorityHigh() {
        var expectedPriority = Priority.HIGH;
        this.appData = this.factory.createAppointmentData(this.description, this.duration, this.priority);
        assertThat(this.appData.getPriority()).isEqualTo(expectedPriority);
    }
}
