package pl.coderslab.programmingSchool.dao;

import pl.coderslab.programmingSchool.models.UserGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserGroupDao {

    public static void saveGroupToDb(Connection conn, UserGroup userGroup) throws SQLException { //Zapis grupy do bazy danych

        if (userGroup.getId() == 0) { // Zapis
            String query = "INSERT INTO user_group (name) \n" +
                    "VALUES (?); ";
            PreparedStatement preparedStatement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, userGroup.getName());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                userGroup.setId(generatedKeys.getInt(1));
            }

        } else { //Modyfikacja

            String query = "UPDATE user_group SET name = ? WHERE id = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, userGroup.getName());
            preparedStatement.setInt(2, userGroup.getId());
            preparedStatement.executeUpdate();
        }

    }


    public static void deleteGroup (Connection conn, UserGroup userGroup) throws SQLException { // USuwanie grupy

        if (userGroup.getId() != 0) {
            String query = "DELETE FROM user_group WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, userGroup.getId());
            preparedStatement.executeUpdate();
            userGroup.setId(0);
        }
    }


    public static UserGroup loadGroupByID (Connection conn, int id) throws SQLException { //Wczytanie grupy po ID

        String query = "SELECT * FROM user_group WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) {
            UserGroup group = new UserGroup();
            group.setId(resultSet.getInt("id"));
            group.setName(resultSet.getString("name"));
            return group;
        }

        return null;
    }

    public static ArrayList<Integer> loadGroupIDs (Connection conn) throws SQLException {  // Pobranie wszystkich id grup. Używane w klasie User w funkcji checkGroupID

        ArrayList<Integer> groupsIDs = new ArrayList<>();
        String query = "SELECT id FROM user_group";
        PreparedStatement preparedStatement = conn.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int groupID = resultSet.getInt("id");
            groupsIDs.add(groupID);
        }

        return groupsIDs;
    }

    public static ArrayList<UserGroup> loadAllGroups (Connection conn) throws SQLException { //Wczytanie wszystkich grup

        ArrayList<UserGroup> groups = new ArrayList<>();
        String query = "SELECT * FROM user_group";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            UserGroup group = new UserGroup();
            group.setId(resultSet.getInt("id"));
            group.setName(resultSet.getString("name"));
            groups.add(group);
        }

        return groups;
    }



}
