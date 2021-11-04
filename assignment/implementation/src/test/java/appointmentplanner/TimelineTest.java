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

        this.illegalInstantiatedTimeline = new TimeLineImpl(start, end, appointments);

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

        this.instantiatedTimeline = new TimeLineImpl(start, end);

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


    @Test
    public void canAddAppointmentOfDuration() {
        var fits = instantiatedTimeline.canAddAppointmentOfDuration(Duration.ofMinutes(60));

        assertThat(fits).isTrue();
    }

    @Test
    public void putAppointment() {
        var factory = new APFactory();
        var originalTimeSlot = factory.between(start, end);
        var appointmentDuration = Duration.ofMinutes(120);
        var timeSlot = factory.between(start.plusSeconds(appointmentDuration.toSeconds()), end);

        var paramMap = new HashMap();
        paramMap.put("OriginalTimeSlot", originalTimeSlot);
        paramMap.put("AppointmentSlot", appointment);
        paramMap.put("NextTimeSlot", timeSlot);

        this.instantiatedTimeline.putAppointment(paramMap);

        SoftAssertions.assertSoftly(softly -> {
            assertThat(this.instantiatedTimeline.gapStream().anyMatch((ts -> ts.equals(originalTimeSlot)))).isFalse();
            assertThat(this.instantiatedTimeline.contains(appointment)).isTrue();
            assertThat(this.instantiatedTimeline.gapStream().anyMatch((ts -> ts.equals(timeSlot)))).isTrue();
        });
    }

    @Test
    public void putAppointmentNullAppointment() {
        var factory = new APFactory();
        var originalTimeSlot = factory.between(start, end);
        var appointmentDuration = Duration.ofMinutes(120);
        var timeSlot = factory.between(start.plusSeconds(appointmentDuration.toSeconds()), end);
        var paramMap = new HashMap();

        paramMap.put("OriginalTimeSlot", originalTimeSlot);
        paramMap.put("AppointmentSlot", null);
        paramMap.put("TimeSlot", timeSlot);

        this.instantiatedTimeline.putAppointment(paramMap);
        var test = this.instantiatedTimeline.gapStream().peek(System.out::println).count();
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(this.instantiatedTimeline.contains(appointment)).isFalse();
            softly.assertThat(this.instantiatedTimeline.gapStream().anyMatch((ts -> ts.equals(timeSlot)))).isFalse();
            softly.assertThat(this.instantiatedTimeline.gapStream().anyMatch((ts -> ts.equals(originalTimeSlot)))).isTrue();
        });
    }

    @ParameterizedTest
    @CsvSource({
            "13,00",
            "15,00",
            "8,00"
    })
    public void addAppointmentLocalTime(int hours, int minutes) {
        var localTime = LocalTime.of(hours, minutes);
        var localDay = LocalDay.now();

        var appointment = this.instantiatedTimeline.addAppointment(localDay, mockedAppointmentData, localTime, TimePreference.EARLIEST_AFTER).get();
        assertThat(this.instantiatedTimeline.findAppointments((val1 -> val1.equals(appointment))).get(0)).isEqualTo(appointment);
    }

    @Test
    public void getGapsMultipleGaps() {
        instantiatedTimeline.addAppointment(localDay, mockedAppointmentData, LocalTime.of(9, 0));
        instantiatedTimeline.addAppointment(localDay, mockedAppointmentData, LocalTime.of(12, 0));
        instantiatedTimeline.addAppointment(localDay, mockedAppointmentData, LocalTime.of(15, 0));
        assertThat(instantiatedTimeline.getGapsFitting(Duration.ofMinutes(60)).size()).isEqualTo(4);
    }

    @ParameterizedTest
    @CsvSource({
            "UNSPECIFIED, true",
            "EARLIEST, true",
            "UNSPECIFIED, false",
            "EARLIEST, false"
    })
    public void addAppointmentTimePreferenceUnspecifiedFirst(TimePreference timePreference, boolean fullParams) {
        var localDay = LocalDay.now();
        when(mockedAppointmentData.getDuration()).thenReturn(Duration.ofMinutes(120));

        Appointment returnedAppointment;
        if (fullParams) {
            returnedAppointment = this.instantiatedTimeline.addAppointment(localDay, mockedAppointmentData, null, timePreference).get();
            this.instantiatedTimeline.addAppointment(localDay, mockedAppointmentData, null, null).get();
        } else {
            returnedAppointment = this.instantiatedTimeline.addAppointment(localDay, mockedAppointmentData, timePreference).get();
            this.instantiatedTimeline.addAppointment(localDay, mockedAppointmentData, (TimePreference) null).get();
        }
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(returnedAppointment.getAppointmentData()).isEqualTo(mockedAppointmentData);
            softly.assertThat(this.instantiatedTimeline.appointmentStream().findFirst().get().getAppointmentData()).isEqualTo(mockedAppointmentData);
            softly.assertThat(this.instantiatedTimeline.appointmentStream().count()).isEqualTo(2);
            softly.assertThat(this.instantiatedTimeline.appointmentStream().findFirst().get().getRequest().getStartTime()).isEqualTo(LocalTime.ofInstant(start, localDay.getZone()));
        });
    }
    
}
