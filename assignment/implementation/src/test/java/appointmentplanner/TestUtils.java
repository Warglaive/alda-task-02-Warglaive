package appointmentplanner;

import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtils {
    @SafeVarargs
    public static <T> void verifyEqualsAndHashCode(T ref, T equal, T... unEqual) {
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(ref.equals("test")).isFalse();
            softly.assertThat(ref.equals(equal)).isTrue();

            for (T ueq : unEqual) {
                softly.assertThat(ref).isNotEqualTo(ueq);
            }
            assertThat(ref.hashCode()).isEqualTo(equal.hashCode());
        });
    }
}
