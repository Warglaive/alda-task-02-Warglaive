package appointmentplanner;

import appointmentplanner.api.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TimelineTest {

    private TimeSlot timeSlot;

    private Stream illegalAppointmentStream;
    private Stream appointmentStream;
    private DoublyLinkedList<TimeSlot> appointments;
    private Instant start, end;
    private Timeline illegalInstantiatedTimeline;
    private TimeLineImpl instantiatedTimeline;

    private AppointmentData mockedAppointmentData;
    private AppointmentRequest mockedAppointmentRequest;
    private TimeSlot mockedTimeSlot;
    private AppointmentImpl appointment;
    private LocalDay localDay;

    @BeforeEach
    public void setUp() {
        var factory = new APFactory();
        this.localDay = LocalDay.now();
        this.appointments = new DoublyLinkedList<TimeSlot>();
        this.start = LocalDay.now().ofLocalTime(LocalTime.parse("08:00"));
        this.end = LocalDay.now().ofLocalTime(LocalTime.parse("18:00"));

        this.illegalInstantiatedTimeline = new TimeLineImpl(start,end,appointments);

        this.mockedAppointmentData = mock(Appointment.class);
        this.mockedAppointmentRequest = mock(AppointmentRequest.class);
        this.mockedTimeSlot = mock(TimeSlot.class);

        when(mockedAppointmentData.getDuration()).thenReturn(Duration.ofMinutes(120));

        appointment = new AppointmentImpl(
                mock(AppointmentData.class),
                mock(AppointmentRequest.class),
                mock(TimeSlot.class));

        timeSlot = new TimeslotImpl(start, end);

        this.appointments.addFront(appointment);
        this.appointments.addFront(this.timeSlot);

        illegalAppointmentStream = illegalInstantiatedTimeline.appointmentStream();

        this.instantiatedTimeline = new TimeLineImpl(start,end);

    }
    @Test
    public void getNrOfAppointments() {
        assertThat(illegalInstantiatedTimeline.getNrOfAppointments()).isEqualTo(1);
    }

    @Test
    public void appointmentStream() {
        assertThat(illegalAppointmentStream
                .allMatch(timeSlot -> timeSlot instanceof Appointment)
        ).isTrue();
    }

    @Test
    public void appointmentStreamCount() {
        assertThat(illegalAppointmentStream.count()).isEqualTo(1);
    }

    @Test
    public void contains() {
        var contains = illegalInstantiatedTimeline.contains(appointment);
        assertThat(contains).isTrue();
    }
    @Test
    public void containsFalse() {
        var contains = illegalInstantiatedTimeline.contains(new AppointmentImpl(
                mock(AppointmentData.class),
                mock(AppointmentRequest.class),
                new TimeslotImpl(start, end)
        ));

        assertThat(contains).isFalse();
    }

    @Test
    public void getGapsFittingSimple() {
        var requiredDuration = Duration.ofMinutes(60);
        var fittingTimeslots = instantiatedTimeline.getGapsFitting(requiredDuration);

        SoftAssertions.assertSoftly(softly -> {
            assertThat(fittingTimeslots.get(0).getStart()).isEqualTo(start);
            assertThat(fittingTimeslots.get(0).getEnd()).isEqualTo(end);
        });
    }

    @Test
    public void getGapsFittingSimpleReversed() {
        var requiredDuration = Duration.ofMinutes(60);
        var fittingTimeslots = instantiatedTimeline.getGapsFittingReversed(requiredDuration);

        SoftAssertions.assertSoftly(softly -> {
            assertThat(fittingTimeslots.get(0).getStart()).isEqualTo(start);
            assertThat(fittingTimeslots.get(0).getEnd()).isEqualTo(end);
        });
    }

    @Test
    public void getGapsFittingSimpleFalse() {
        var requiredDuration = Duration.ofHours(24);
        var fittingTimeslots = instantiatedTimeline.getGapsFitting(requiredDuration);

        SoftAssertions.assertSoftly(softly -> {
            assertThat(fittingTimeslots.isEmpty()).isTrue();
        });
    }
}
