package roomescape.dao;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;
import roomescape.domain.Reservation;

@Repository
public class ReservationInMemoryRepository implements ReservationRepository {

    private final Map<Long, Reservation> reservations;
    private final AtomicLong idCount;

    public ReservationInMemoryRepository() {
        this.reservations = new ConcurrentHashMap<>();
        idCount = new AtomicLong(1L);
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        Reservation newReservation = new Reservation(idCount.getAndIncrement(), reservation);
        reservations.put(newReservation.getId(), newReservation);
        return newReservation;
    }

    @Override
    public List<Reservation> findAll() {
        return reservations.entrySet()
                .stream()
                .map(entry -> new Reservation(entry.getKey(), entry.getValue()))
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        reservations.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return reservations.containsKey(id);
    }

    @Override
    public void deleteAll() {
        reservations.clear();
    }
}
