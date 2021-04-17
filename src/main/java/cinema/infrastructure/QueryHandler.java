package cinema.infrastructure;

import cinema.readmodel.Query;
import cinema.readmodel.QueryResult;
import cinema.readmodel.ReadModel;
import cinema.readmodel.ReadModelID;
import cinema.readmodel.movielist.AsksMoviesInATimeWindow;
import cinema.readmodel.movielist.MovieList;
import cinema.readmodel.reservedseats.AskForReservedSeats;
import cinema.readmodel.reservedseats.ReservedSeats;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class QueryHandler {

    private final Map<ReadModelID, ReadModel> readModelsMap;

    public QueryHandler(List<ReadModel> readModels) {
        this.readModelsMap = new Hashtable<>();
        for (ReadModel readModel : readModels) {
            this.readModelsMap.put(readModel.getId(), readModel);
        }
    }

    public QueryResult handle(Query query) {
        if (query instanceof AsksMoviesInATimeWindow) {
            MovieList movieList = (MovieList) readModelsMap.get(ReadModelID.MovieList);
            return movieList.asksMoviesInATimeWindow((AsksMoviesInATimeWindow)query);
        }
        if (query instanceof AskForReservedSeats) {
            ReservedSeats reservedSeats = (ReservedSeats) readModelsMap.get(ReadModelID.ReservedSeats);
            return reservedSeats.askForReservedSeats((AskForReservedSeats)query);
        }
        return null;
    }

}
