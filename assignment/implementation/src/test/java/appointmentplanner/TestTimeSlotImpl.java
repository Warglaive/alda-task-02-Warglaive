package appointmentplanner;

import appointmentplanner.api.TimeSlot;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThatCode;

public class TestTimeSlotImpl {
    /**
     * getter and setter test
     */
    @Test
    public void constructorAssignsCorrect() {
        Instant startTime = Instant.parse("2020-09-17T10:00:00Z");
        Instant endTime = Instant.parse("2020-09-17T10:24:00Z");

        TimeSlot timeSlot = new TimeslotImpl(
                startTime, endTime
        );

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(timeSlot.getStart()).isSameAs(startTime);
            softly.assertThat(timeSlot.getEnd()).isSameAs(endTime);
        });
    }

    @ParameterizedTest
    @CsvSource({
            ", 2020-09-17T10:24:00Z, Null values are not accepted!",
            "2020-09-17T10:00:00Z, , Null values are not accepted!",
            "2020-09-17T10:24:00Z, 2020-09-17T10:00:00Z, End must be after start"
    })
    public void constructorThrowsIAException(Instant startTime, Instant endTime, String exceptionMessage) {
        ThrowableAssert.ThrowingCallable constructorCall = () -> new TimeslotImpl(startTime, endTime);

        assertThatCode(constructorCall)
                .hasMessage(exceptionMessage)
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
