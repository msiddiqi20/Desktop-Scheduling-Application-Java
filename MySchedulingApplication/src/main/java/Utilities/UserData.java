package Utilities;

import DAO.UsersQuery;
import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**This is the abstract User Data class.*/
public abstract class UserData {

    private static Users user;
    private static int numberedUser = 0;
    private static ObservableList<Users> allUsers = FXCollections.observableArrayList();
    private static HashMap<Integer, Users> usersHM = new HashMap<>();
    private static ObservableList <String> userIDs = FXCollections.observableArrayList();

    /**This is the User getter.
     * This method returns the user.
     * @return Returns the user.*/
    public static Users getUser() {
        return user;
    }

    /**This is the User Setter.
     * This method sets the current user.
     * @param currentUser The current user logged into the system.*/
    public static void setUser(Users currentUser) {
        user = currentUser;
    }

    /**This is the Numbered User getter.
     * This method gets the Numbered User.
     * @return Returns the numbered user.*/
    public static int getNumberedUser(){
        return numberedUser;
    }

    /**This is the Numbered User Setter.
     * This method sets the numbered user by incrementing.*/
    public static void setNumberedUser(){
        numberedUser++;
    }

    /**This is the All Users getter.
     * This method returns all the Users.
     * @return This returns all the users in the form of an ObservableList (Users)*/
    public static ObservableList<Users> getAllUsers(){
        return allUsers;
    }

    /**This is the Users Hash Map getter.
     * This method returns the Users Hash Map.
     * @return This returns all the users in the form of a Hash Map (Integer, Users).*/
    public static HashMap<Integer, Users> getUsersHM(){
        return usersHM;
    }

    /**This is the User IDs getter.
     * This method returns all the User IDs.
     * @return This returns all the user ids in the form of an ObservableList (String)*/
    public static ObservableList <String> getUserIDs(){
        return userIDs;
    }

    /**This is the All Users setter.
     * This method calls the Users Query to retrieve and set all the local user data.
     * @throws SQLException Throws SQLException.*/
    public static void setAllUsers() throws SQLException {

        ResultSet resultSet = UsersQuery.read();

        while (resultSet.next()) {

            int userID = resultSet.getInt("User_ID");
            String userName = resultSet.getString("User_Name");

            allUsers.add(new Users(userID, userName));

        }

        for (Users user : allUsers) {

            usersHM.put(user.getUserID(), user);
            userIDs.add(String.valueOf(user.getUserID()));

        }

    }

}
