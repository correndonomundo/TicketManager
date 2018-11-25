import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketManager implements Ticketing{

    MySQLHandler mySQLHandler;
    User currentUser;

    public TicketManager() throws SQLException {
       mySQLHandler = new MySQLHandler();
       this.currentUser = null;
    }

    @Override
    public Ticket createTicket(Ticket ticket) throws SQLException {
        ticket.setAuthor_id(currentUser.getUser_ID());
         mySQLHandler.addTicket(ticket);
        return ticket;
    }

    @Override
    public boolean assignTo(Ticket ticket, int responsabil_ID) {
        ticket.setResponsabil_id(responsabil_ID);
       mySQLHandler.updateTicketResp(ticket);
       return true;
    }

    @Override
    public boolean statusUpdate(Ticket ticket, String ticketStatus) {
        if(ticket.getStatus().equals("w")) {
            mySQLHandler.updateTicketStatusWIP(ticket, ticketStatus);
        } else if(ticket.getStatus().equals("c")){
            mySQLHandler.updateTicketStatusCLO(ticket, ticketStatus);
        }
        else{
            System.out.println("nu ai ales o opiune valida");
        }


        return false;
    }


    @Override
    public List<Ticket> getAllByAuthor(int author_id) {
        try {
            return mySQLHandler.getTicketsByAuthor(author_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Ticket> getAllByResponsible(int responsabil_id) {
        try {
            return mySQLHandler.getTicketsByResponsible(responsabil_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean register(User user) {
        return mySQLHandler.addUser(user);
    }

    @Override
    public boolean login(User loginUser) {
        if(mySQLHandler.loginUser(loginUser)) {
            currentUser = loginUser;
            return true;
        }
       return false;
    }

    @Override
    public boolean logout() {
        return false;
    }
}
