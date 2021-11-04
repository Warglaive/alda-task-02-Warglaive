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

    }
}
