package roomescape.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import roomescape.domain.Reservation;
import roomescape.service.dto.ReservationDto;

public class InMemoryReservationRepository implements ReservationRepository {

    private final Map<Long, Reservation> database;
    private final AtomicLong idCount = new AtomicLong(1L);

    public InMemoryReservationRepository(Map<Long, Reservation> database) {
        this.database = database;
    }

    public InMemoryReservationRepository() {
        this.database = new HashMap<>();
    }

    @Override
    public Reservation addReservation(ReservationDto reservationDto) {
        Reservation entity = reservationDto.toEntity(idCount.getAndIncrement());
        database.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Reservation> findAll() {
        return database.values()
                .stream()
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        database.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return database.containsKey(id);
    }

    @Override
    public void deleteAll() {
        database.clear();
    }
}