package roomescape.service.dto;

import java.time.LocalTime;
import roomescape.domain.TimeSlot;
import roomescape.utils.TimeFormatter;

public class TimeSlotDto {

    private final Long id;
    private final String time;

    public TimeSlotDto(Long id, String time) {
        this.id = id;
        this.time = time;
    }

    public TimeSlotDto(Long id, LocalTime time) {
        this(id, TimeFormatter.format(time));
    }

    public TimeSlotDto(LocalTime time) {
        this(null, time);
    }

    public static TimeSlotDto from(TimeSlot timeSlot) {
        return new TimeSlotDto(timeSlot.getId(), timeSlot.getTime());
    }

    public TimeSlot toEntity(Long id) {
        return new TimeSlot(id, time);
    }

    public TimeSlot toEntity() {
        if (id == null) {
            throw new IllegalStateException("ID가 존재하지 않습니다.");
        }
        return toEntity(id);
    }

    public Long getId() {
        return id;
    }

    public String getTime() {
        return time;
    }
}