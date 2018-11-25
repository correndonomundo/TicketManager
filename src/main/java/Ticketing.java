import java.sql.SQLException;
import java.util.List;

public interface Ticketing extends LoginCapable {

    public Ticket createTicket(Ticket ticket) throws SQLException;

    public boolean assignTo(Ticket t, int responsabil_id);

    public boolean statusUpdate(Ticket t, String ticketStatus);

    List<Ticket> getAllByAuthor(int author_id);

    List<Ticket> getAllByResponsible(int responsabil_id);


}
