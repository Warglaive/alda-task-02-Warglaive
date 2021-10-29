package appointmentplanner;

import appointmentplanner.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

    private AppointmentRequest appointmentRequest;
    private AppointmentData appointmentData;

    @BeforeEach
    void setUp() {
        this.appointmentData = Mockito.mock(AppointmentData.class);
        this.appointmentRequest = Mockito.mock(AppointmentRequest.class);

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
