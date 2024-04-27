package roomescape.console.repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;
import roomescape.core.domain.Reservation;
import roomescape.core.domain.ReservationRepository;

@Repository
public class ReservationInMemoryRepository implements ReservationRepository {

    private final AtomicLong idCounter;
    private final Map<Long, Reservation> reservations;

    public ReservationInMemoryRepository() {
        this.idCounter = new AtomicLong(1);
        this.reservations = new ConcurrentHashMap<>();
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        long id = idCounter.getAndIncrement();
        Reservation savedReservation = reservation.withId(id);
        reservations.put(id, savedReservation);
        return savedReservation;
    }

    @Override
    public List<Reservation> findAll() {
        return List.copyOf(reservations.values());
    }

    @Override
    public void deleteById(long id) {
        reservations.remove(id);
    }

    @Override
    public void deleteAll() {
        reservations.clear();
    }
}
