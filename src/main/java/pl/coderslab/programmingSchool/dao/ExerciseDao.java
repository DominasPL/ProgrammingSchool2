package pl.coderslab.programmingSchool.dao;

import pl.coderslab.programmingSchool.models.Exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExerciseDao {

    public static void saveToDb(Connection conn, Exercise exercise) throws SQLException { // Zapis do bazdy danych

        if (exercise.getId() == 0) {

            String query = "INSERT INTO exercise (title, description) \n" +
                    "VALUES (?, ?);";
            PreparedStatement preparedStatement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,exercise.getTitle());
            preparedStatement.setString(2,exercise.getDescription());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                exercise.setId(generatedKeys.getInt(1));
            }

        } else {  // Modyfikacja

            String query = "UPDATE exercise SET title = ?, description = ? WHERE id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, exercise.getTitle());
            preparedStatement.setString(2, exercise.getDescription());
            preparedStatement.setInt(3, exercise.getId());
            preparedStatement.executeUpdate();


        }

    }

    public static void deleteExercise(Connection conn, Exercise exercise) throws SQLException { // Usunięcie zadania z bazdy danych

        if (exercise.getId() != 0) {

            String query = "DELETE FROM exercise WHERE id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, exercise.getId());
            preparedStatement.executeUpdate();
            exercise.setId(0);
        }

    }

    public static Exercise loadExerciseById(Connection conn, int id) throws  SQLException { //Pobranie zadania po id

        String query = "SELECT * FROM exercise WHERE id = ?;";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Exercise exercise = new Exercise();
            exercise.setId(resultSet.getInt("id"));
            exercise.setTitle(resultSet.getString("title"));
            exercise.setDescription(resultSet.getString("description"));
            return exercise;
        }

        return null;

    }

    public static ArrayList<Integer> loadExercisesIDs (Connection conn) throws SQLException {  // Pobranie wszystkich id zadań. Używane w klasie Solution w funkcji checkExerciseID

        ArrayList<Integer> exercisesIDs = new ArrayList<>();
        String query = "SELECT id FROM exercise";
        PreparedStatement preparedStatement = conn.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int exerciseID = resultSet.getInt("id");
            exercisesIDs.add(exerciseID);
        }

        return exercisesIDs;
    }


    public static ArrayList<Exercise> loadAllExercises(Connection conn) throws SQLException { //Pobranie wszystkich zadan z bazy danych

        ArrayList<Exercise> exercises = new ArrayList<>();
        String query = "SELECT * FROM exercise;";
        PreparedStatement preparedStatement = conn.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Exercise exercise = new Exercise();
            exercise.setId(resultSet.getInt("id"));
            exercise.setTitle(resultSet.getString("title"));
            exercise.setDescription(resultSet.getString("description"));

            exercises.add(exercise);
        }

        return exercises;

    }

    public static ArrayList<Integer> loadExercisesIdsWithUserSolution(Connection conn, int id) throws SQLException { //Pobranie wszystkich zadan do których użytkownik nie dodał rozwiązania

        ArrayList<Integer> exercises = new ArrayList<>();
        String query = "SELECT exercise_id FROM solution\n" +
                "WHERE user_id = ?\n" +
                "GROUP BY exercise_id;";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            exercises.add(resultSet.getInt("exercise_id"));
        }

        return exercises;

    }




}
