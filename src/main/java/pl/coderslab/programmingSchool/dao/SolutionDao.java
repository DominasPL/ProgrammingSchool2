package pl.coderslab.programmingSchool.dao;

import pl.coderslab.programmingSchool.models.Solution;
import pl.coderslab.programmingSchool.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class SolutionDao {


    public static void saveSolutionToDb(Connection conn, Solution solution) throws SQLException { // Zapis rozwiązania do bazy danych

        if (solution.getId() == 0) {

            String query = "INSERT INTO solution (exercise_id, user_id, created, updated, description) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, solution.getExercise_id());
            preparedStatement.setInt(2, solution.getUser_id());
            preparedStatement.setString(3, solution.getCreated());
            preparedStatement.setString(4, solution.getUpdated());
            preparedStatement.setString(5, solution.getDescription());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                solution.setId(generatedKeys.getInt(1));
            }

        } else { //Modyfikacja rowiązania

            String query = "UPDATE solution SET exercise_id = ?, user_id = ?, created = ?, updated = ?, description = ? WHERE id = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, solution.getExercise_id());
            preparedStatement.setInt(2, solution.getUser_id());
            preparedStatement.setString(3, solution.getCreated());
            preparedStatement.setString(4, solution.getUpdated());
            preparedStatement.setString(5, solution.getDescription());
            preparedStatement.setInt(6, solution.getId());
            preparedStatement.executeUpdate();

        }


    }

    public static void deleteSolution(Connection conn, Solution solution) throws SQLException { // Usuwanie rozwiązania z bazy danych
        if (solution.getId() != 0) {
            String sql = "DELETE FROM solution WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, solution.getId());
            preparedStatement.executeUpdate();
            solution.setId(0);
        }
    }

    public static int checkExerciseID (int exercise_id) throws SQLException { // Sprawdzenie czy podany id zadania znajduje się w bazie danych.

        try (Connection conn = DbUtil.getConnection()) {
            ArrayList<Integer> exercisesIDs = ExerciseDao.loadExercisesIDs(conn);

            if (!exercisesIDs.contains(exercise_id)) { // Jeżeli nie ma takiego numeru zadania to exercise_id = 0
                exercise_id = 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exercise_id;
    }

    public static int checkUserID (int user_id) throws SQLException { // Sprawdzenie czy podany id usera znajduje się w bazie danych.

        try (Connection conn = DbUtil.getConnection()) {
            ArrayList<Integer> usersIDs = UserDao.loadUsersIDs(conn);

            if (!usersIDs.contains(user_id)) { // Jeżeli podana wartość  jest nieprawidłowa to user_id = 0
                user_id = 0;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user_id;
    }



    public static Solution loadSolutionById(Connection conn, int id) throws SQLException { // Wczytanie rozwiazania o konkretnym ID

        String query = "SELECT * FROM solution WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Solution solution = new Solution();
            solution.setId(resultSet.getInt("id"));
            solution.setExercise_id(resultSet.getInt("exercise_id"));
            solution.setUser_id(resultSet.getInt("user_id"));
            solution.setCreated(resultSet.getString("created"));
            solution.setUpdated(resultSet.getString("updated"));
            solution.setDescription(resultSet.getString("description"));
            return solution;
        }

        return null;

    }


    public static ArrayList<Solution> loadAllSolutions(Connection conn) throws SQLException { //Wczytanie wszystkich uzytkownikow
        ArrayList<Solution> solutions = new ArrayList<>();
        String query = "SELECT * FROM solution";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Solution solution = new Solution();
            solution.setId(resultSet.getInt("id"));
            solution.setExercise_id(resultSet.getInt("exercise_id"));
            solution.setUser_id(resultSet.getInt("user_id"));
            solution.setCreated(resultSet.getString("created"));
            solution.setUpdated(resultSet.getString("updated"));
            solution.setDescription(resultSet.getString("description"));


            solutions.add(solution);
        }

        return solutions;

    }

    public static ArrayList<Solution> loadAllSolutionsLimited(Connection conn, int limit) throws SQLException { //Wczytanie wszystkich uzytkownikow
        ArrayList<Solution> solutions = new ArrayList<>();
        String query = "SELECT * FROM solution \n" +
                "ORDER BY exercise_id DESC\n" +
                "LIMIT ?;";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, limit);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Solution solution = new Solution();
            solution.setId(resultSet.getInt("id"));
            solution.setExercise_id(resultSet.getInt("exercise_id"));
            solution.setUser_id(resultSet.getInt("user_id"));
            solution.setCreated(resultSet.getString("created"));
            solution.setUpdated(resultSet.getString("updated"));
            solution.setDescription(resultSet.getString("description"));


            solutions.add(solution);
        }

        return solutions;

    }



    public static ArrayList<Solution> loadAllSolutionsByUserId(Connection conn, int id) throws SQLException { // Wczytanie wszystkich rozwiazan danego uzytkownika
        ArrayList<Solution> solutions = new ArrayList<>();
        String query = "SELECT * FROM solution WHERE user_id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Solution solution = new Solution();
            solution.setId(resultSet.getInt("id"));
            solution.setExercise_id(resultSet.getInt("exercise_id"));
            solution.setUser_id(resultSet.getInt("user_id"));
            solution.setCreated(resultSet.getString("created"));
            solution.setUpdated(resultSet.getString("updated"));
            solution.setDescription(resultSet.getString("description"));

            solutions.add(solution);

        }

        return solutions;

    }

    public static ArrayList<Solution> loadAllSortedExerciseSolutions(Connection conn, int id) throws SQLException { // Wczytanie wszystkich rozwiazan danego zadania (posortowane)
        ArrayList<Solution> solutions = new ArrayList<>();
        String query = "SELECT * FROM solution WHERE exercise_id = ? ORDER BY created DESC;";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Solution solution = new Solution();
            solution.setId(resultSet.getInt("id"));
            solution.setExercise_id(resultSet.getInt("exercise_id"));
            solution.setUser_id(resultSet.getInt("user_id"));
            solution.setCreated(resultSet.getString("created"));
            solution.setUpdated(resultSet.getString("updated"));
            solution.setDescription(resultSet.getString("description"));

            solutions.add(solution);

        }

        return solutions;

    }





}
