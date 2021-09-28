package appointmentplanner;

import appointmentplanner.api.Timeline;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TimelineTest {
    @Test
    @BeforeEach
    void constructorTest() {
        TimelineImpl timeline = new TimelineImpl();
        AssertionsForClassTypes.assertThat(timeline).isExactlyInstanceOf(TimelineImpl.class);
    }
}
