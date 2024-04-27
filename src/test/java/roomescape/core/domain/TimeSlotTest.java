package roomescape.core.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import roomescape.core.domain.TimeSlot;

class TimeSlotTest {

    @Test
    @DisplayName("동등성을 비교한다.")
    void equalsTest() {
        TimeSlot timeSlot = new TimeSlot(1L, "15:30");
        assertThat(timeSlot).isEqualTo(new TimeSlot(1L, "16:30"));
    }
}
