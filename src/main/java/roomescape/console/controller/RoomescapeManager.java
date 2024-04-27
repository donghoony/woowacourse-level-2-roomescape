package roomescape.console.controller;

import jakarta.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.Map;
import roomescape.web.view.InputView;
import roomescape.web.view.command.ManagementCommand;

public abstract class RoomescapeManager {

    protected final Map<ManagementCommand, CommandExecutor> commandExecutors;

    protected RoomescapeManager() {
        this.commandExecutors = new EnumMap<>(ManagementCommand.class);
    }

    @PostConstruct
    private void prepareCommandExecutors() {
        commandExecutors.putAll(Map.of(
                ManagementCommand.CREATE, this::create,
                ManagementCommand.DELETE, this::delete,
                ManagementCommand.BACK, () -> {}
        ));
    }

    public void menu() {
        ManagementCommand command;
        do {
            showAllResults();
            command = InputView.getManagementCommand();
            commandExecutors.get(command).execute();
        } while (!command.isBack());
    }

    protected abstract void showAllResults();

    public abstract void create();

    public abstract void delete();
}
