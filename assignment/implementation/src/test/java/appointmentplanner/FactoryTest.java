package appointmentplanner;

import appointmentplanner.api.*;

import java.time.*;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeAll;

/**
 * Example service invocation and factory test.
 * 
 * @author Pieter van den Hombergh {@code p.vandenhombergh@fontys.nl}
 */
public class FactoryTest {
    
    static AbstractAPFactory fac;
    @BeforeAll
    static void assumeFactory(){
        fac= ServiceFinder.getFactory();
    }
    
    @Test
    void factoryCreatesDayPlan(){
        LocalDay day = LocalDay.now();
        LocalDayPlan ldp = fac.createLocalDayPlan( day, LocalTime.parse("08:00"), LocalTime.parse("17:30"));
        assertThat(ldp).as( fac.getClass().getName()+" returns null object").isNotNull();
    }

    @Test
    void factoryCreatesDayPlanZoneIdTimeline() {
        var timeline = mock(Timeline.class);
        var zoneId = mock(ZoneId.class);
        var localDate = LocalDate.now();

        assertThat(fac.createLocalDayPlan(zoneId, localDate, timeline))
                .isNotNull();
    }

    @Test
    void createAppointmentData3Args() {
        AppointmentData actual = fac.createAppointmentData(
                "mock Description", Duration.ofMinutes(100), Priority.HIGH
        );

        AppointmentData expected = new AppointmentDataImpl(
                Duration.ofMinutes(100), "mock Description", Priority.HIGH
        );

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void createAppointmentData2Args() {
        AppointmentData actual = fac.createAppointmentData(
                "mock Description", Duration.ofMinutes(100)
        );

        AppointmentData expected = new AppointmentDataImpl(
                Duration.ofMinutes(100), "mock Description", Priority.LOW
        );

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void createAppointmentRequest3Args() {
        LocalTime startTime = LocalTime.now();
        AppointmentData appointmentData = mock(AppointmentData.class);
        TimePreference timePreference = TimePreference.EARLIEST;

        AppointmentRequest actual = fac.createAppointmentRequest(
                appointmentData, startTime, timePreference
        );

        AppointmentRequest expected = new AppointmentRequestImpl(
                startTime, appointmentData, timePreference
        );

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testBetween() {
        Instant start = Instant.now();
        Instant end = Instant.now().plusSeconds(10);
        TimeSlot expected = new TimeslotImpl(start, end);

        assertThat(fac.between(start, end)).isEqualToComparingFieldByField(expected);
    }
}
