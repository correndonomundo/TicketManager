import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleBasedUI {

    Scanner  scan = new Scanner(System.in);
    String prompt = ">";
    TicketManager ticketManager = new TicketManager();


    public ConsoleBasedUI() throws SQLException {}


    public boolean start() throws SQLException {
         boolean quit = false;
        while(!quit){
            System.out.println("Introdu r pentru register, l pentu login, e pentru exit");
            String userOption = scan.next();
            switch(userOption){
                case "r":
                    System.out.println("Introdu username");
                    String usernameForCreation = scan.next();
                    System.out.println("Introdu email");
                    String emailForCreation = scan.next();
                    System.out.println("Introdu pass");
                    String passwordForCreation = scan.next();
                    System.out.println("Introdu prenume");
                    String first_nameForCreation = scan.next();
                    System.out.println("Introdu nume");
                    String last_nameForCreation = scan.next();
                    User user = new User(usernameForCreation, emailForCreation, passwordForCreation, first_nameForCreation, last_nameForCreation);
                    ticketManager.register(user);
                    prompt = user + ">";
                    System.out.println(prompt);
                    break;
                case"l":
                    System.out.println("Introdu username");
                    String usernameForLogIn = scan.next();
                    System.out.println("Introdu pass");
                    String passwordForLogIn = scan.next();
                    User userToLogIn = new User(usernameForLogIn,  passwordForLogIn);
                    ticketManager.login(userToLogIn);
                    prompt = userToLogIn + ">";
                    promptForTickets();
                    break;
                case "e":
                    prompt = "<";
                    quit = true;
                    break;
                default:
                    System.out.println("Nu ai introdus o optiune valida");
                    break;
            }
        }
        return true;
    }

    public boolean promptForTickets() throws SQLException {
        boolean exitTicketMenu = false;
        while(!exitTicketMenu){
            System.out.println("Introdu:" +
                    "c pentru a crea un tichet, " +
                    "a pentru a aloca ticket, " +
                    "u pentru a updata ticket " +
                    "va pentru a vedea tichetele dupa autor" +
                    "vr pentru a vedea ticketele dupa responsabil" +
                     "e pentru a iesi");
            String optiuneUtilizator = scan.next();
            switch(optiuneUtilizator){
                case "c":
                    System.out.println("Introdu titlu");
                    String title = scan.nextLine();
                    System.out.println("Introdu descriere");
                    String description  = scan.nextLine();
                    Ticket ticket = new Ticket(title, description);
                    ticketManager.createTicket(ticket);
                    break;
                case "a":
                    System.out.println("Introdu id ticket");
                   int ticket_id = scan.nextInt();
                    System.out.println("Introdu id-ul userului caruia i se aloca ticketul");
                   int responsabil_ID = scan.nextInt();
                    Ticket ticketForUpdate = new Ticket(ticket_id, responsabil_ID);
                    ticketManager.assignTo(ticketForUpdate, responsabil_ID);
                    break;
                case "u":
                    System.out.println("Introdu id ticket");
                    int ticket_idForStatus = scan.nextInt();
                    System.out.println("Introdu noul status: w pentru work in progress, c penru closed");
                    scan.nextLine();
                    String ticketStatus = scan.nextLine();
                    Ticket ticketForStatus = new Ticket(ticket_idForStatus);
                    ticketForStatus.setStatus(ticketStatus);
                    ticketManager.statusUpdate(ticketForStatus, ticketStatus);
                    break;
                case "va":
                    System.out.println("Introduceti id autor dupa care se face filtrarea");
                    int author_id = scan.nextInt();
                    ticketManager.getAllByAuthor(author_id);
                    break;
                case "vr":
                    System.out.println("Introduceti id responsabil dupa care se face filtrarea");
                    int responsabil_id = scan.nextInt();
                    ticketManager.getAllByResponsible(responsabil_id);
                    break;
                case "e":
                    exitTicketMenu = true;
                    break;
                default:
                    System.out.println("Nu ai introdus o optiune valida");
                    break;
            }

        }
        return true;
    }
}
