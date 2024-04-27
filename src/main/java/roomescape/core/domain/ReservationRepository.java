package roomescape.core.domain;

import java.util.List;

public interface ReservationRepository {

    Reservation addReservation(Reservation reservation);

    List<Reservation> findAll();

    void deleteById(long id);

    void deleteAll();
}
