package appointmentplanner;

import appointmentplanner.api.AppointmentData;
import appointmentplanner.api.LocalDay;
import appointmentplanner.api.Priority;
import appointmentplanner.api.TimePreference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.*;

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
    void ctorTest() {
        assertThat(this.appointmentRequest).isExactlyInstanceOf(AppointmentRequestImpl.class);
    }

    @Test
    void getStartTimeTest() {
        assertThat(this.appointmentRequest.getStartTime()).isEqualTo(LocalTime.of(14, 20));
    }

    @Test
    void getAppDataTest() {
        assertThat(this.appData).isEqualTo(this.appointmentRequest.getAppointmentData());
    }

    @Test
    void getTimePreference() {
        var expectedTimePreference = TimePreference.UNSPECIFIED;
        assertThat(this.appointmentRequest.getTimePreference()).isEqualTo(expectedTimePreference);
    }

    @Test
    void getDurationTest() {
        assertThat(this.appData.getDuration()).isEqualTo(this.duration);
    }
}
