package roomescape.core.controller.dto;

import roomescape.core.domain.Reservation;
import roomescape.core.domain.TimeSlot;

public record ReservationRequest(String name, String date, long timeSlotId) {

    public Reservation toEntity(TimeSlot timeSlot) {
        return new Reservation(name, date, timeSlot);
    }
}
