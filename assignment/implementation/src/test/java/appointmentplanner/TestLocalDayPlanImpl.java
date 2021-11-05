package appointmentplanner;

import appointmentplanner.api.LocalDay;
import appointmentplanner.api.LocalDayPlan;
import appointmentplanner.api.Timeline;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.mock;

public class TestLocalDayPlanImpl {

    @Test
    public void constructorStandard024() {
        var timeline = mock(Timeline.class);
        var localDate = LocalDate.now();
        var zoneId = mock(ZoneId.class);
        var localDay = new LocalDay(zoneId, localDate);

        var localDayPlan = new LocalDayPlanImpl(zoneId, localDate, timeline);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(localDayPlan.getTimeline()).isEqualTo(timeline);
            softly.assertThat(localDayPlan.getDay()).isEqualTo(localDay);
        });
    }

    @Test
    public void constructorSpecifiedTime() {
        var localDay = LocalDay.now();
        var start = localDay.ofLocalTime(LocalTime.parse("01:00"));
        var end = localDay.ofLocalTime(LocalTime.parse("02:00"));
        var timeline = new TimeLineImpl(start, end);

        var localDayPlan = new LocalDayPlanImpl(localDay, start, end);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(localDayPlan.getDay()).isEqualTo(localDay);
//            softly.assertThat(localDayPlan.getTimeline()).isEqualToComparingFieldByField(timeline);
            softly.assertThat(localDayPlan.earliest()).isEqualTo(start);
            softly.assertThat(localDayPlan.tooLate()).isEqualTo(end);
        });
    }

    @ParameterizedTest
    @CsvSource({
       //     "-1, 0",
            "00:01, 00:00",
//            "00:00, 00:00",
            //"1, 86441"
    })
    public void constructorThrowsExceptionFalseInstant(String startTime, String endTime) {
        var localDay = LocalDay.now();
        Instant start = localDay.ofLocalTime(LocalTime.parse(startTime));
        Instant end = localDay.ofLocalTime(LocalTime.parse(endTime));

        ThrowableAssert.ThrowingCallable exceptionCode = () -> {
            new LocalDayPlanImpl(
                    localDay,
                    start,
                    end
            );
        };

        assertThatCode(exceptionCode)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Start has to be before end and positive. End has to be after start and positive. End has to be before 24.");
    }
}