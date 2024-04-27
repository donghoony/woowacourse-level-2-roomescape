package roomescape.core.service;

import java.time.LocalTime;
import java.util.List;
import org.springframework.stereotype.Service;
import roomescape.core.controller.dto.TimeSlotCreationRequest;
import roomescape.core.controller.dto.TimeSlotCreationResponse;
import roomescape.core.domain.TimeSlot;
import roomescape.core.domain.TimeSlotRepository;

@Service
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;

    public TimeSlotService(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    public List<TimeSlotCreationResponse> getAllTimes() {
        return timeSlotRepository.findAllOrderByTimeAscending()
                .stream()
                .map(TimeSlotCreationResponse::from)
                .toList();
    }

    public TimeSlotCreationResponse addTime(TimeSlotCreationRequest request) {
        TimeSlot timeSlot = request.toEntity();
        validateUniqueTime(timeSlot.getTime());
        TimeSlot savedTimeSlot = timeSlotRepository.addTimeSlot(timeSlot);
        return TimeSlotCreationResponse.from(savedTimeSlot);
    }

    private void validateUniqueTime(LocalTime time) {
        if (timeSlotRepository.existsByTime(time)) {
            throw new IllegalArgumentException("시간을 중복하여 등록할 수 없습니다.");
        }
    }

    public void removeTime(long id) {
        timeSlotRepository.deleteById(id);
    }
}
