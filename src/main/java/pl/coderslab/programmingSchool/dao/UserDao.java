package pl.coderslab.programmingSchool.dao;

import pl.coderslab.programmingSchool.models.User;
import pl.coderslab.programmingSchool.models.UserGroup;
import pl.coderslab.programmingSchool.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {

    public static void saveUserToDb(Connection conn, User user) throws SQLException { //Zapis nowego uzykownika do bazy danych

        if (user.getId() == 0) { // Zapis
            String query = "INSERT INTO users (group_id, username, password, email) VALUES (?, ?, ?, ?) ";
            PreparedStatement preparedStatement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, user.getGroup_id());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }

        } else { // Modyfikacja

            String query = "UPDATE users SET group_id = ?, username = ?, password = ?, email = ? WHERE id = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, user.getGroup_id());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, user.getId());
            preparedStatement.executeUpdate();

        }


    }

    public static void deleteUser(Connection conn, User user) throws SQLException { // Usuwanie uzytkownika z bazy danych
        if (user.getId() != 0) {
            String sql = "DELETE FROM users WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
            user.setId(0);
        }
    }

    public static User loadUserById(Connection conn, int id) throws SQLException { // Wczytanie użytkownika o konkretnym ID

        String query = "SELECT * FROM users WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            User usr1 = new User();
            usr1.setId(resultSet.getInt("id"));
            usr1.setGroup_id(resultSet.getInt("group_id"));
            usr1.setUsername(resultSet.getString("username"));
            usr1.setPassword(resultSet.getString("password"));
            usr1.setEmail(resultSet.getString("email"));
            return usr1;

        }

        return null;

    }

    public static ArrayList<Integer> loadUsersIDs (Connection conn) throws SQLException {  // Pobranie wszystkich id uzytkownikow. Używane w klasie Solution w funkcji checkUserID

        ArrayList<Integer> usersIDs = new ArrayList<>();
        String query = "SELECT id FROM users";
        PreparedStatement preparedStatement = conn.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int userID = resultSet.getInt("id");
            usersIDs.add(userID);
        }

        return usersIDs;
    }


    public static ArrayList<User> loadAllUsers(Connection conn) throws SQLException { //Wczytanie wszystkich uzytkownikow
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            User usr1 = new User();
            usr1.setId(resultSet.getInt("id"));
            usr1.setGroup_id(resultSet.getInt("group_id"));
            usr1.setUsername(resultSet.getString("username"));
            usr1.setPassword(resultSet.getString("password"));
            usr1.setEmail(resultSet.getString("email"));

            users.add(usr1);
        }

        return users;

    }

    public static ArrayList<User> loadUsersByUserName(Connection conn, String username) throws SQLException { //Pobieranie uzytkownikow z bazy danych o wskazanym username
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM users WHERE username = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            User usr1 = new User();
            usr1.setId(resultSet.getInt("id"));
            usr1.setGroup_id(resultSet.getInt("group_id"));
            usr1.setUsername(resultSet.getString("username"));
            usr1.setPassword(resultSet.getString("password"));
            usr1.setEmail(resultSet.getString("email"));

            users.add(usr1);
        }

        return users;

    }

    public static ArrayList<User> loadAllUsersFromGroup(Connection conn, int id) throws SQLException { //Pobieranie uzytkownikow z bazy danych o wskazanym username
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM users\n" +
                "WHERE group_id = ?;";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            User usr1 = new User();
            usr1.setId(resultSet.getInt("id"));
            usr1.setGroup_id(resultSet.getInt("group_id"));
            usr1.setUsername(resultSet.getString("username"));
            usr1.setPassword(resultSet.getString("password"));
            usr1.setEmail(resultSet.getString("email"));

            users.add(usr1);
        }

        return users;

    }

    public static int checkGroupID (int group_id) throws SQLException { // Sprawdzenie czy podany id grupy znajduje się w bazie danych. Jeżeli nie to id grupy ustawiane jest na 1.

        try (Connection conn = DbUtil.getConnection()) {
            ArrayList<Integer> groupsIDs = UserGroupDao.loadGroupIDs(conn);

            if (!groupsIDs.contains(group_id)) {
                System.out.println("Grupa o podanym id nie istnieje. Użytkownik został automatycznie przypisany do grupy o id = 1 !!!");
                group_id = 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return group_id;

    }

















}
