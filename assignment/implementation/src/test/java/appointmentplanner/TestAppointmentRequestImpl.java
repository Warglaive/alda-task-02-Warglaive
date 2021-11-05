package appointmentplanner;

import appointmentplanner.api.AppointmentData;
import appointmentplanner.api.AppointmentRequest;
import appointmentplanner.api.Priority;
import appointmentplanner.api.TimePreference;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;
import java.time.LocalTime;

import static appointmentplanner.TestUtil.verifyEqualsAndHashCode;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestAppointmentRequestImpl {
    LocalTime startTime;
    AppointmentData appointmentData;
    TimePreference timePreference;

    @BeforeEach
    public void callSetUpBefore() {
        setUp();
    }

    @BeforeEach
    public void callSetUpBeforeEach() {
        setUp();
    }
    public void setUp() {
        startTime = LocalTime.now();
        appointmentData = mock(AppointmentData.class);
        timePreference = TimePreference.EARLIEST;

        when(appointmentData.getDescription()).thenReturn("mock description");
        when(appointmentData.getPriority()).thenReturn(Priority.LOW);
        when(appointmentData.getDuration()).thenReturn(Duration.ofMinutes(10));
    }

    @Test
    public void constructorCorrectValues() {
        AppointmentRequest appointmentRequest = new AppointmentRequestImpl(
                startTime, appointmentData, timePreference);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(appointmentRequest.getAppointmentData()).isSameAs(appointmentData);
            softly.assertThat(appointmentRequest.getStartTime()).isSameAs(startTime);
        });
    }

    @Test
    public void constructorMissingTimePreference() {
        AppointmentRequest appointmentRequest = new AppointmentRequestImpl(
                startTime, appointmentData, null);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(appointmentRequest.getAppointmentData()).isSameAs(appointmentData);
            softly.assertThat(appointmentRequest.getStartTime()).isSameAs(startTime);
            softly.assertThat(appointmentRequest.getTimePreference()).isSameAs(TimePreference.UNSPECIFIED);
        });
    }


    @ParameterizedTest
    @CsvSource({
//            "true, false, false, Null values are not being accepted!",
            "false, true, false, Null values are not being accepted!",
//            "false, false, true, Null values are not being accepted!"
    })
    public void constructorThrowsIAException(boolean startTimeNull, boolean appointDataNull,
                                             boolean timePreferenceNull, String exceptionMessage) {
        ThrowableAssert.ThrowingCallable constructorCall;
        AppointmentRequest appointmentRequest;

        if (startTimeNull) {
            constructorCall = () -> new AppointmentRequestImpl(
                    null, appointmentData, timePreference);
        } else if (appointDataNull) {
            constructorCall = () -> new AppointmentRequestImpl(
                    startTime, null, timePreference);
        } else {
            constructorCall = () -> new AppointmentRequestImpl(
                    startTime, appointmentData, null);
        }

        assertThatCode(constructorCall)
                .hasMessage(exceptionMessage)
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void gettersCorrectValues() {
        AppointmentRequest appointmentRequest = new AppointmentRequestImpl(
                startTime, appointmentData, timePreference);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(appointmentRequest.getDescription()).isEqualTo("mock description");
            softly.assertThat(appointmentRequest.getPriority()).isEqualTo(Priority.LOW);
            softly.assertThat(appointmentRequest.getDuration()).isEqualTo(Duration.ofMinutes(10));
            softly.assertThat(appointmentRequest.getTimePreference()).isEqualTo(timePreference);
        });
    }

    @Test
    public void testEqualsAndHashCode() {
        var ref = new AppointmentRequestImpl(startTime, appointmentData, timePreference);
        var equal = new AppointmentRequestImpl(startTime, appointmentData, timePreference);
        var unEqualStartTime = new AppointmentRequestImpl(LocalTime.now().minusHours(2), appointmentData, timePreference);
        var unEqualAppointmentData = new AppointmentRequestImpl(startTime, mock(AppointmentDataImpl.class), timePreference);
        var unEqualTimePreference = new AppointmentRequestImpl(startTime, appointmentData, TimePreference.LATEST);

        verifyEqualsAndHashCode(ref,equal,unEqualStartTime,unEqualAppointmentData,unEqualTimePreference);
    }
}
