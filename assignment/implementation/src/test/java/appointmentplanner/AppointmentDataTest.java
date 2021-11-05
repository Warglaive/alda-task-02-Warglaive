package appointmentplanner;

import appointmentplanner.api.AppointmentData;
import appointmentplanner.api.Priority;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;


import static appointmentplanner.TestUtils.verifyEqualsAndHashCode;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class AppointmentDataTest {
    @ParameterizedTest
    @CsvSource({
            "200, blablacar, LOW",
            "2000, blablacar, MEDIUM",
            "20000, blablacar, HIGH",
    })
    public void constructorAssignsCorrect(int durationMinutes, String description, Priority priority) {
        Duration duration = Duration.ofMinutes(durationMinutes);
        AppointmentData appointmentData = new AppointmentDataImpl(duration, description, priority);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(appointmentData.getDuration()).isEqualTo(duration);
            softly.assertThat(appointmentData.getDescription()).isEqualTo(description);
            softly.assertThat(appointmentData.getPriority()).isEqualTo(priority);
        });
    }

    @Test
    public void constructorDefaultDescription() {
        Duration duration = Duration.ofMinutes(200);
        Priority priority = Priority.HIGH;

        AppointmentData appointmentData = new AppointmentDataImpl(duration, priority);

        assertThat(appointmentData.getDescription()).isEqualTo("No description");
    }

    @ParameterizedTest
    @CsvSource({
            "-10, blablacar, LOW, 'Invalid duration, please give a positive value!'",
            ", blablacar, LOW, Null values are not being accepted!",
            "10, , LOW, Null values are not being accepted!",
            "10, blablacar, ,Null values are not being accepted!",
            "10, ' ', LOW, Null values are not being accepted!"
    })
    public void constructorThrowsIAException(Integer durationMinutes, String description, Priority priority, String exceptionMessage) {
        Duration duration;

        if (durationMinutes != null) {
            duration = Duration.ofMinutes(durationMinutes);
        } else {
            duration = null;
        }

        ThrowingCallable constructorCall = () -> new AppointmentDataImpl(duration, description, priority);
        assertThatCode(constructorCall)
                .hasMessage(exceptionMessage)
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void constructorDurationDescription() {
        AppointmentData actual = new AppointmentDataImpl(
                Duration.ofMinutes(100), "mock Description"
        );

        AppointmentData expected = new AppointmentDataImpl(
                Duration.ofMinutes(100), "mock Description", Priority.LOW
        );

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testEqualsAndHashCode() {
        Duration mockedDuration = Duration.ofSeconds(10);
        String mockDescription = "mock";
        Priority refPriority = Priority.HIGH;

        var ref = new AppointmentDataImpl(mockedDuration, mockDescription, refPriority);
        var equal = new AppointmentDataImpl(mockedDuration, mockDescription, refPriority);
        var unEqualDuration = new AppointmentDataImpl(Duration.ofSeconds(12), mockDescription, refPriority);
        var unEqualDescription = new AppointmentDataImpl(mockedDuration, "falseMock", refPriority);
        var unEqualPriority = new AppointmentDataImpl(mockedDuration, mockDescription, Priority.LOW);
        verifyEqualsAndHashCode(ref, equal, unEqualDuration, unEqualDescription, unEqualPriority);
    }
}
