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


    }


}
