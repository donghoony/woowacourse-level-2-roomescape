package roomescape.console.controller;

import jakarta.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import roomescape.web.view.InputView;
import roomescape.web.view.OutputView;
import roomescape.web.view.command.AdminCommand;

@Component
public class ConsoleNavigator {

    private final ReservationConsoleController reservationController;
    private final TimeSlotConsoleController timeSlotController;
    private final Map<AdminCommand, CommandExecutor> commandExecutors;

    public ConsoleNavigator(ReservationConsoleController reservationController,
                            TimeSlotConsoleController timeSlotController) {
        this.reservationController = reservationController;
        this.timeSlotController = timeSlotController;
        this.commandExecutors = new EnumMap<>(AdminCommand.class);
    }

    public void run() {
        AdminCommand command;
        do {
            OutputView.printAdminMenu();
            command = InputView.getAdminCommand();
            commandExecutors.get(command).execute();
        } while (!command.isExit());
    }

    @PostConstruct
    private void prepareCommandExecutors() {
        commandExecutors.putAll(Map.of(
                AdminCommand.RESERVATION_MANAGEMENT, reservationController::menu,
                AdminCommand.TIME_SLOT_MANAGEMENT, timeSlotController::menu,
                AdminCommand.EXIT, () -> {}
        ));
    }
}
