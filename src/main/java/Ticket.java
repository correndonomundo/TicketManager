public class Ticket {

    private  int ticket_id;
    private String title;
    private String description;
    private  int author_id;
    private  int responsabil_id;
    private String status;


    public Ticket(String title, String description) {
        this.title = title;
        this.description = description;
    }
    public Ticket (int ticket_id){
        this.ticket_id = ticket_id;
    }

    public Ticket (){

    }

    public Ticket (int ticket_id, int responsabil_id){
        this.ticket_id = ticket_id;
        this.responsabil_id = responsabil_id;
    }

    public Ticket(String title, String description, int author_id,
                  int responsabil_id, String status) {
        this.title = title;
        this.description = description;
        this.author_id = author_id;
        this.responsabil_id = responsabil_id;
        this.status = status;
    }

    public Ticket(int ticket_id, String title, String description, int author_id,
                  int responsabil_id, String status) {
        this.ticket_id = ticket_id;
        this.title = title;
        this.description = description;
        this.author_id = author_id;
        this.responsabil_id = responsabil_id;
        this.status = status;
    }

    public String getInsertStmtTicket() {
        return "INSERT INTO ticket (title, description, author_id, responsabil_id, status)" +
                "VALUES(" + "'" + title + "'" + "," + "'" + description + "'" + "," + "'" + author_id
                + "'" + "," + "'" + responsabil_id + "'" + "," + "'" + status + "'" + ")";
    }

    public String getUpdateStmtTicket(){
        return "UPDATE ticket SET responsabil_id = " + "'" + responsabil_id + "'" + " WHERE ticket_id = " + ticket_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setResponsabil_id(int responsabil_id) {
        this.responsabil_id = responsabil_id;
    }

    public String getUpdateStmtStatusWIP(int ticket_id){
        return "UPDATE ticket SET status = 'WIP' WHERE ticket_id = " + ticket_id;
    }
    public String getUpdateStmtStatusCLO(int ticket_id){
        return "UPDATE ticket SET status = 'CLO' WHERE ticket_id = " + ticket_id;
    }

    public String getFilterByAuthorStmt(int author_id){
        return "SELECT ticket_id, title, description, author_id, responsabil_id, status FROM ticket WHERE author_id = " + author_id;
    }

    public String getFilterByResponsiblerStmt(int responsabil_id){
        return "SELECT ticket_id, title, description, author_id, responsabil_id, status FROM ticket WHERE author_id = " + author_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
