package appointmentplanner;

import appointmentplanner.api.AppointmentData;
import appointmentplanner.api.Priority;
import appointmentplanner.api.TimePreference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;

public class AppointmentRequestTest {

    /**
     * AppointmentData arguments
     */
    private String description = "test";
    private Duration duration = Duration.ofHours(2);
    private Priority priority = Priority.HIGH;
    /**
     * AppointmentRequest arguments
     */
    //
    private APFactory factory;
    private AppointmentData appData;
    private LocalTime prefStart;
    private TimePreference fallBack;

    @BeforeEach
    void setUp() {
        this.factory = new APFactory();

        this.appData = this.factory.createAppointmentData(this.description, this.duration);
        this.prefStart = LocalTime.of(14, 20);
        this.fallBack = TimePreference.EARLIEST;

    }

    @Test
    void ctorTest() {
        this.factory.createAppointmentRequest(this.appData, this.prefStart, this.fallBack);
    }

    @Test
    void getStartTest() {

    }
}
