import java.sql.SQLException;

public class Main {

    public static void main(String[] args){


        ConsoleBasedUI consoleBasedUI = null;
        try {
            consoleBasedUI = new ConsoleBasedUI();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            consoleBasedUI.start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
