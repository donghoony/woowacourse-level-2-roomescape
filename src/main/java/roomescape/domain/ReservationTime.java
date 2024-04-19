package roomescape.domain;

import java.time.LocalTime;
import java.util.Objects;

public class ReservationTime {

    private final Long id;
    private final LocalTime time;

    public ReservationTime(Long id, LocalTime time) {
        this.id = id;
        this.time = time;
    }

    public ReservationTime(Long id, String time) {
        this(id, LocalTime.parse(time));
    }

    public ReservationTime(LocalTime time) {
        this(null, time);
    }

    public ReservationTime(String time) {
        this(null, time);
    }

    public Long getId() {
        return id;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReservationTime that = (ReservationTime) o;
        return Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time);
    }
}
