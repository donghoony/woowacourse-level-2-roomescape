package roomescape.console.repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;
import roomescape.core.domain.TimeSlot;
import roomescape.core.domain.TimeSlotRepository;

@Repository
public class TimeSlotInMemoryRepository implements TimeSlotRepository {

    private final AtomicLong idCounter;
    private final Map<Long, TimeSlot> timeSlots;

    public TimeSlotInMemoryRepository() {
        this.idCounter = new AtomicLong(1);
        this.timeSlots = new ConcurrentHashMap<>();
    }

    @Override
    public TimeSlot addTimeSlot(TimeSlot timeSlot) {
        long id = idCounter.getAndIncrement();
        TimeSlot savedTimeSlot = timeSlot.withId(id);
        timeSlots.put(id, savedTimeSlot);
        return savedTimeSlot;
    }

    @Override
    public List<TimeSlot> findAllOrderByTimeAscending() {
        return List.copyOf(timeSlots.values());
    }

    @Override
    public Optional<TimeSlot> findById(long id) {
        return Optional.ofNullable(timeSlots.get(id));
    }

    @Override
    public void deleteById(long id) {
        timeSlots.remove(id);
    }

    @Override
    public boolean existsByTime(LocalTime time) {
        return timeSlots.values()
                .stream()
                .anyMatch(timeSlot -> timeSlot.hasTimeOf(time));
    }

    @Override
    public void deleteAll() {
        timeSlots.clear();
    }
}
