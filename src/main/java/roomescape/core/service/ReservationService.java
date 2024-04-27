package roomescape.core.service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.springframework.stereotype.Service;
import roomescape.core.controller.dto.ReservationRequest;
import roomescape.core.controller.dto.ReservationResponse;
import roomescape.core.domain.Reservation;
import roomescape.core.domain.ReservationRepository;
import roomescape.core.domain.TimeSlot;
import roomescape.core.domain.TimeSlotRepository;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TimeSlotRepository timeSlotRepository;
    private final Clock clock;

    public ReservationService(ReservationRepository reservationRepository,
                              TimeSlotRepository timeSlotRepository,
                              Clock clock) {
        this.reservationRepository = reservationRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.clock = clock;
    }

    public List<ReservationResponse> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(ReservationResponse::from)
                .toList();
    }

    public ReservationResponse scheduleReservation(ReservationRequest request) {
        TimeSlot timeSlot = timeSlotRepository.findById(request.timeSlotId())
                .orElseThrow(() -> new IllegalArgumentException("예약 시간이 존재하지 않습니다."));
        validateRequestDateAfterCurrentTime(LocalDate.parse(request.date()), timeSlot.getTime());

        Reservation reservation = request.toEntity(timeSlot);
        Reservation savedReservation = reservationRepository.addReservation(reservation);
        return ReservationResponse.from(savedReservation);
    }

    private void validateRequestDateAfterCurrentTime(LocalDate reservationDate, LocalTime reservationTime) {
        LocalDateTime reservationDateTime = LocalDateTime.of(reservationDate, reservationTime);
        if (reservationDateTime.isBefore(LocalDateTime.now(clock))) {
            throw new IllegalArgumentException("과거의 시간을 예약할 수 없습니다.");
        }
    }

    public void cancelReservation(long id) {
        reservationRepository.deleteById(id);
    }
}
