package Model;

/**This is the Users class.*/
public class Users {

    /**This is the Users class constructor.
     * @param userID This is the user id.
     * @param userName This is the username.*/
    public Users(int userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    private int userID;
    private String userName;

    /**This is the User ID getter.
     * This method returns the ID of the user.
     * @return The user ID (int).*/
    public int getUserID() {
        return userID;
    }

    /**This is the User ID setter.
     * This method sets the ID for the user.
     * @param userID The desired user ID (int).*/
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**This is the Username getter.
     * This method returns the name of the user.
     * @return The user name (String).*/
    public String getUserName() {
        return userName;
    }

    /**This is the Username setter.
     * This method sets the name for the user.
     * @param userName The desired user name (String).*/
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
