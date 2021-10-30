package appointmentplanner;

import appointmentplanner.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AppointmentTest {

    AppointmentImpl appointment;
    /**
     * AppointmentData constructor arguments
     */
    private String description = "test";
    private Duration duration = Duration.ofHours(2);
    private Priority priority = Priority.HIGH;
    /**
     * AppointmentRequest constructor arguments
     */
    private AppointmentData appData;
    private LocalTime prefStart;
    private TimePreference fallBack;

    /**
     *
     */
    private APFactory factory;
    private AppointmentRequest appointmentRequest;

    @BeforeEach
    void setUp() {
        this.factory = new APFactory();

        this.prefStart = LocalTime.of(14, 20);
        this.fallBack = TimePreference.EARLIEST;
        this.appData = this.factory.createAppointmentData(this.description, this.duration);
        this.appointmentRequest = factory.createAppointmentRequest(this.appData, this.prefStart, this.fallBack);
        this.appointment = new AppointmentImpl(this.appointmentRequest);
    }

    @Test
    void constructorTest() {
        assertThat(this.appointment).isExactlyInstanceOf(AppointmentImpl.class);
    }

    @Test
    void getRequestTest() {
        var expected = new AppointmentRequestImpl(this.appData, this.prefStart, this.fallBack);
        assertThat(this.appointment.getRequest()).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getDurationTest() {
        assertThat(this.appointment.getDuration()).isEqualTo(this.duration);
    }

    @Test
    void getDescriptionTest() {
        assertThat(this.appointment.getDescription()).isEqualTo(this.description);
    }

    @Test
    void getPriorityTest() {
        assertThat(this.appointment.getPriority()).isEqualTo(Priority.LOW);
    }

    @Test
    void getAppointmentDataTest() {
        assertThat(this.appointment.getAppointmentData()).isEqualTo(this.appData);
    }

    @Test
    void getStartTest() {
        var expected = LocalDay.now().ofLocalTime(this.appointmentRequest.getStartTime());
        assertThat(this.appointment.getStart()).isEqualTo(expected);
    }

    @Test
    void getEndTest() {
        var expected = LocalDay.now().ofLocalTime(this.appointmentRequest.getStartTime()
                .plus(this.appointmentRequest.getDuration()));

        assertThat(this.appointment.getEnd()).isEqualTo(expected);
    }
}
