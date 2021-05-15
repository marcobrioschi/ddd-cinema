package cinema.infrastructure;

import cinema.command.Command;
import cinema.command.ConfirmReservation;
import cinema.command.CreatePlannedScreening;
import cinema.command.ReserveSeats;
import cinema.domain.PlannedScreening;
import cinema.events.Event;

import java.util.List;

public class CommandHandler {

    private final PlannedScreeningRepository plannedScreeningRepository;
    private final LocalClock localClock;
    private final IdentifierGenerator identifierGenerator;

    public CommandHandler(
            PlannedScreeningRepository plannedScreeningRepository,
            LocalClock localClock,
            IdentifierGenerator identifierGenerator
    ) {
        this.plannedScreeningRepository = plannedScreeningRepository;
        this.localClock = localClock;
        this.identifierGenerator = identifierGenerator;
    }

    public void handle(Command command) {

        List<Event> currentEntityHistory = plannedScreeningRepository.loadPlannedScreeningEvents(command.getTargetEntityId());
        PlannedScreening.PlannedScreeningStatus plannedScreeningStatus = new PlannedScreening.PlannedScreeningStatus(currentEntityHistory);
        PlannedScreening plannedScreening = new PlannedScreening(plannedScreeningStatus);

        List<Event> publishedEvents = null;
        if (command instanceof CreatePlannedScreening) {
            CreatePlannedScreening createPlannedScreening = (CreatePlannedScreening)command;
            publishedEvents = PlannedScreening.createPlannedScreen( // TODO: static, don't require an aggregate instance
                    createPlannedScreening.getMovie(),
                    createPlannedScreening.getSchedulingTime(),
                    createPlannedScreening.getRoom(),
                    identifierGenerator.generateAnID()
            );
        }
        if (command instanceof ReserveSeats) {
            ReserveSeats reserveSeats = (ReserveSeats)command;
            publishedEvents = plannedScreening.reserveSeats(
                    reserveSeats.getCustomer(),
                    reserveSeats.getSeats(),
                    localClock.now(),
                    identifierGenerator.generateAnID()
            );
        }
        if (command instanceof ConfirmReservation) {
            ConfirmReservation confirmReservation = (ConfirmReservation) command;
            publishedEvents = plannedScreening.confirmReservation(
                    confirmReservation.getReservationId(),
                    localClock.now()
            );
        }

        // TODO NOTE: in Marco H. example is the aggregate that pushes the events. In his solution how the command handler can be a 'unit of work'?
        if (publishedEvents != null) {
            plannedScreeningRepository.persistPlannedScreeningEvents(publishedEvents);
        }

    }

}
