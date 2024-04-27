package roomescape.core.controller.dto;

import java.time.LocalTime;
import roomescape.core.domain.TimeSlot;

public record TimeSlotCreationResponse(long id, LocalTime startAt) {

    public static TimeSlotCreationResponse from(TimeSlot timeSlot) {
        return new TimeSlotCreationResponse(
                timeSlot.getId(),
                timeSlot.getTime()
        );
    }
}
