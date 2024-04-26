package roomescape.core.controller.dto;

import roomescape.core.domain.TimeSlot;

public record TimeSlotCreationRequest(String startAt) {

    public TimeSlot toEntity() {
        return new TimeSlot(startAt);
    }
}
