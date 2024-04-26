package roomescape.console.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import roomescape.core.controller.dto.ReservationRequest;
import roomescape.core.controller.dto.ReservationResponse;
import roomescape.core.service.ReservationService;
import roomescape.web.view.InputView;
import roomescape.web.view.OutputView;

@Controller
public class ReservationConsoleController extends ManagementController {

    private final ReservationService reservationService;

    public ReservationConsoleController(ReservationService reservationService) {
        super();
        this.reservationService = reservationService;
    }

    @Override
    protected void showAllResults() {
        List<ReservationResponse> responses = reservationService.getAllReservations();
        OutputView.printReservations(responses);
        OutputView.printReservationManagementMenu();
    }

    @Override
    public void create() {
        ReservationRequest request = InputView.createReservationRequest();
        ReservationResponse response = reservationService.scheduleReservation(request);
        OutputView.printReservationCreationResponse(response);
    }

    @Override
    public void delete() {
        long reservationId = InputView.inputDeleteReservationId();
        reservationService.cancelReservation(reservationId);
        OutputView.printDeleteReservationMessage();
    }
}
