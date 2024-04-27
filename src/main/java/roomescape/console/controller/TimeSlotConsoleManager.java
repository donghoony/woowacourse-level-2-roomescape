package roomescape.console.controller;

import java.util.List;
import org.springframework.stereotype.Component;
import roomescape.core.controller.dto.TimeSlotCreationRequest;
import roomescape.core.controller.dto.TimeSlotCreationResponse;
import roomescape.core.service.TimeSlotService;
import roomescape.web.view.InputView;
import roomescape.web.view.OutputView;

@Component
public class TimeSlotConsoleManager extends RoomescapeManager {

    private final TimeSlotService timeSlotService;

    public TimeSlotConsoleManager(TimeSlotService timeSlotService) {
        super();
        this.timeSlotService = timeSlotService;
    }

    @Override
    protected void showAllResults() {
        List<TimeSlotCreationResponse> responses = timeSlotService.getAllTimes();
        OutputView.printTimeSlots(responses);
        OutputView.printTimeSlotManagementMenu();
    }

    @Override
    public void create() {
        TimeSlotCreationRequest request = InputView.createTimeSlotRequest();
        TimeSlotCreationResponse response = timeSlotService.addTime(request);
        OutputView.printTimeSlotCreationResponse(response);
    }

    @Override
    public void delete() {
        long timeSlotId = InputView.inputDeleteTimeSlotId();
        timeSlotService.removeTime(timeSlotId);
        OutputView.printDeleteTimeSlotMessage();
    }
}
