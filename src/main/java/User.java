public class User {

    private int user_ID;
    private  String username;
    private  String email;
    private   String password;
    private  String first_name;
    private  String last_name;


    public User(String username, String email, String password, String first_name, String last_name){
        this.username = username;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
    }
    public User(String username, String password){
        this.username = username;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getInsertStmtUser(){
       return "INSERT INTO user (username, email, password, first_name, last_name)" +
                "VALUES(" + "'" + username + "'" + "," + "'" + email + "'" + "," + "'" + password
               + "'" + "," + "'" + first_name + "'" + "," + "'" + last_name+ "'" + ")";
    }

    public String getLogInStmtUser(){
        return "SELECT *  FROM user WHERE  username = " +  "'" + username + "' " + " AND " + "password = " +  "'" + password + "'";
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public int getUser_ID() {

        return user_ID;
    }
}
