package roomescape.core.domain;

import java.time.LocalDate;
import java.util.Objects;

public class ReservationDate {

    private final LocalDate date;

    public ReservationDate(LocalDate date) {
        this.date = date;
    }

    public ReservationDate(String date) {
        this(LocalDate.parse(date));
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReservationDate that = (ReservationDate) o;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public String toString() {
        return "ReservationDate{" +
                "date=" + date +
                '}';
    }
}