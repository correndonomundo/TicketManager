import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLHandler {

    private Connection dbConnection;

    public MySQLHandler() throws SQLException {
        dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demologin",
                "root",
                "admin");
    }

    public boolean addUser(User userForAdd) {
      String sqlCmdAdd = userForAdd.getInsertStmtUser();
        boolean DBOpAdd = false;
        try {
            Statement statement = dbConnection.createStatement();
            DBOpAdd = statement.execute(sqlCmdAdd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DBOpAdd;
    }

    public boolean loginUser(User userForLogin) {
        String sqlCmdLogin = userForLogin.getLogInStmtUser();

        boolean DBOpLogin = false;
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCmdLogin);
            if (resultSet.next()){
                int userIdFromDB = resultSet.getInt("user_id");
               // de modificat in loc de setUser_ID vom crea un obiect nou de tipul user care il va copia pe userForLogin si care va adauga si user_ID
                userForLogin.setUser_ID(userIdFromDB);
                System.out.println("Login successful!");
                return true;
            }
            else{
                System.out.println("Wrong username/password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DBOpLogin;
    }

    public boolean addTicket (Ticket newTicket) throws SQLException {
        String sqlCmdAddTicket = newTicket.getInsertStmtTicket();
        boolean DBOpAdd = false;
        try {
            Statement statement = dbConnection.createStatement();
            DBOpAdd = statement.execute(sqlCmdAddTicket);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DBOpAdd;
    }

    public boolean updateTicketResp(Ticket updateTicket){
        String sqlCmdUpdateTicket = updateTicket.getUpdateStmtTicket();
        boolean DBOpLogin = false;
        try {
            Statement statement = dbConnection.createStatement();
                statement.execute(sqlCmdUpdateTicket);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return DBOpLogin;

    }

    public boolean updateTicketStatusWIP(Ticket updateStatus, String ticketStatus){
        String sqlCmdUpdateStatus = updateStatus.getUpdateStmtStatusWIP(updateStatus.getTicket_id());
        boolean DBOpLogin = false;
        try {
            Statement statement = dbConnection.createStatement();
            statement.execute(sqlCmdUpdateStatus);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return DBOpLogin;
    }

    public boolean updateTicketStatusCLO(Ticket updateStatus, String ticketStatus){
        String sqlCmdUpdateStatus = updateStatus.getUpdateStmtStatusCLO(updateStatus.getTicket_id());
        boolean DBOpLogin = false;
        try {
            Statement statement = dbConnection.createStatement();
            statement.execute(sqlCmdUpdateStatus);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return DBOpLogin;
    }

    public List <Ticket> getTicketsByAuthor (int author_id) throws SQLException {
        Ticket ticket = new Ticket();
        String sqlCmdGetByAuthor = ticket.getFilterByAuthorStmt(author_id);

            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCmdGetByAuthor);
            List<Ticket> ticketsByAutor = new ArrayList<Ticket>();
            while (resultSet.next()){
                Ticket ticketToDisplay = new Ticket(resultSet.getInt("ticket_id"),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getInt("author_id"),
                resultSet.getInt("responsible_id"),
                resultSet.getString("status"));
                ticketsByAutor.add(ticketToDisplay);
            }
        return ticketsByAutor;

    }


    public List <Ticket> getTicketsByResponsible (int responsible_id) throws SQLException {
        Ticket ticket = new Ticket();
        String sqlCmdGetByResponsible = ticket.getFilterByResponsiblerStmt(responsible_id);
           Statement statement = dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlCmdGetByResponsible);
        List<Ticket> ticketsByResponsible = new ArrayList<Ticket>();
        while (resultSet.next()){
           Ticket ticketToDisplay = new Ticket(resultSet.getInt("ticket_id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getInt("author_id"),
                    resultSet.getInt("responsible_id"),
                    resultSet.getString("status"));
            ticketsByResponsible.add(ticketToDisplay);
        }
        return ticketsByResponsible;
    }
}
