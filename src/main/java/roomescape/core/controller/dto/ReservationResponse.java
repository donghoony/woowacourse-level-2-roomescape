package roomescape.core.controller.dto;

import java.time.LocalDate;
import roomescape.core.domain.Name;
import roomescape.core.domain.Reservation;
import roomescape.core.domain.ReservationDate;
import roomescape.core.domain.TimeSlot;

public record ReservationResponse(Long id, String name, LocalDate date, TimeSlotCreationResponse timeSlot) {

    public static ReservationResponse from(Reservation reservation) {
        Name name = reservation.getName();
        ReservationDate reservationDate = reservation.getReservationDate();
        TimeSlot timeSlot = reservation.getTimeSlot();
        return new ReservationResponse(
                reservation.getId(),
                name.asText(),
                reservationDate.getDate(),
                TimeSlotCreationResponse.from(timeSlot)
        );
    }
}
