package roomescape.core.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import roomescape.core.domain.Reservation;
import roomescape.core.domain.TimeSlot;

class ReservationTest {

    @Test
    @DisplayName("같은 ID를 가진다면, 서로 동등하다.")
    void equalsOnSameIdTest() {
        Reservation actual = new Reservation(1L, "웨지", "2024-04-20", new TimeSlot("13:00"));
        Reservation expected = new Reservation(1L, "웨지", "2024-04-21", new TimeSlot("12:00"));
        assertThat(actual).isEqualTo(expected);
    }
}
