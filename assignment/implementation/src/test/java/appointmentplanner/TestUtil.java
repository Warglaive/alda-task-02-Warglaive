package appointmentplanner;

import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtil {

    @SafeVarargs
    public static <T> void verifyEqualsAndHashCode(T ref, T equal, T... unEqual) {
        SoftAssertions.assertSoftly(softly -> {

        softly.assertThat(ref.equals(ref)).isTrue();
        softly.assertThat(ref.equals(null)).isFalse();
        softly.assertThat(ref.equals("test")).isFalse();
        softly.assertThat(ref.equals(equal)).isTrue();

        for (var i = 0; i < unEqual.length; i++) {
            T ueq = unEqual[i];
            softly.assertThat(ref).isNotEqualTo(ueq);
        }
        assertThat(ref.hashCode()).isEqualTo(equal.hashCode());
        });
    }

}
