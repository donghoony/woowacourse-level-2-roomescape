package roomescape.core.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TimeSlotTest {

    @Test
    @DisplayName("동등성을 비교한다.")
    void equalsTest() {
        TimeSlot timeSlot = new TimeSlot(1L, "15:30");
        assertThat(timeSlot).isEqualTo(new TimeSlot(1L, "16:30"));
    }

    @Test
    @DisplayName("같은 시각을 가지고 있는지 확인한다.")
    void hasTimeTest() {
        // given
        TimeSlot timeSlot = new TimeSlot(1L, "15:30");
        // when, then
        assertAll(
            () -> assertThat(timeSlot.hasTimeOf(LocalTime.of(15, 30))).isTrue(),
            () -> assertThat(timeSlot.hasTimeOf(LocalTime.of(15, 31))).isFalse()
        );
    }
}
