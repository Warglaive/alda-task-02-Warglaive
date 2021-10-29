package appointmentplanner;

import appointmentplanner.api.Appointment;
import appointmentplanner.api.AppointmentData;
import appointmentplanner.api.Priority;
import appointmentplanner.api.TimePreference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;

public class AppointmentTest {

    AppointmentImpl appointment;
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
        this.appointment = new AppointmentImpl(this.appointmentRequest);
    }
}
